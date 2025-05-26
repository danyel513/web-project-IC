import { HttpClient } from '@angular/common/http';
import { UserService } from '../services/user.service';
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { lastValueFrom } from 'rxjs';

@Component({
  imports: [CommonModule],
  selector: 'app-recommendation',
  templateUrl: './recommendation.component.html',
  standalone: true,
  styleUrls: ['./recommendation.component.css']
})
export class RecommendationComponent{
  username: string = 'test';
  outfitImages: string[] = []; // Store image data URLs
  loading: boolean = false;



  constructor(private http: HttpClient, private userService: UserService) {}

  ngOnInit(): void {
    this.fetchOutfitImages();
  }


  async fetchOutfitImages(): Promise<void> {
    this.loading = true;
    try {
      const outfitUrl = `http://localhost:8080/api/outfits/get/outfit?username=${this.username}`;
      const outfitResponse = await lastValueFrom(this.http.get(outfitUrl, { responseType: 'text' }));

      const ids = outfitResponse.split('/').map(id => parseInt(id, 10));

      const imagePromises = ids.map(id =>
        lastValueFrom(this.http.get(`http://localhost:8080/api/outfits/get/image?id=${id}`, { responseType: 'blob' }))
      );

      const imageBlobs = await Promise.all(imagePromises);

      this.outfitImages = await Promise.all(imageBlobs.map(blob => this.blobToDataURL(blob)));

    } catch (error) {
      console.error('Error fetching outfit images:', error);
    }
    finally {
      this.loading = false;
    }
  }

  private blobToDataURL(blob: Blob): Promise<string> {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.onloadend = () => resolve(reader.result as string);
      reader.onerror = reject;
      reader.readAsDataURL(blob);
    });
  }

  onButtonClick(): void {
    console.log('Button clicked!');
    // Add any action you want here
  }
}
