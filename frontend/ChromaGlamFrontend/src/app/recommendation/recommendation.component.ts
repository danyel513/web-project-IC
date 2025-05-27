import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
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
export class RecommendationComponent {
  username: string = '';
  outfitImages1: string[] = []; // Store image data URLs -- 1
  outfitImages2: string[] = []; // Store image data URLs -- 2
  outfitImages3: string[] = []; // Store image data URLs -- 3
  loading: boolean = false;
  messages = ['Choose one of the outfits below.', 'Choose the one that suits you better.', 'Choose the one you like the most.', 'All outfits are generated with the power of AI.'];
  displayedText = '';
  typingSpeed = 100; // ms per letter
  pauseBetweenMessages = 2000; // ms to wait before next message

  avatars = [
    '/blue_girl.png',
    '/businnesman.png',
    '/girl.png',
    '/man.png',
    '/pink_girl.png'
  ];

  selectedAvatar = this.avatars[0];
  avatarPickerVisible = false;

  constructor(private router: Router, private http: HttpClient, private userService: UserService) {
    this.username = userService.getUsername();
  }

  async ngOnInit(): Promise<void>  {
    this.loading = true;

    // Wait for all data to load before stopping the loader
    const [images1, images2, images3] = await Promise.all([
      this.fetchOutfitImages(),
      this.fetchOutfitImages(),
      this.fetchOutfitImages()
    ]);

    this.outfitImages1 = images1;
    this.outfitImages2 = images2;
    this.outfitImages3 = images3;

    this.loading = false;
    this.startTypingAll();
  }


  toggleAvatarPicker(): void {
    this.avatarPickerVisible = !this.avatarPickerVisible;
  }

  selectAvatar(avatar: string): void {
    this.selectedAvatar = avatar;
    this.avatarPickerVisible = false;
    localStorage.setItem('userAvatar', avatar); // Optional
  }

  async fetchOutfitImages(): Promise<string[]> {
    try {
      const outfitUrl = `http://localhost:8080/api/outfits/get/outfit?username=${this.username}`;
      const outfitResponse = await lastValueFrom(this.http.get(outfitUrl, { responseType: 'text' }));
      const ids = outfitResponse.split('/').map(id => parseInt(id, 10));

      const imageBlobs = await Promise.all(
        ids.map(id => lastValueFrom(this.http.get(`http://localhost:8080/api/outfits/get/image?id=${id}`, { responseType: 'blob' })))
      );

      return await Promise.all(imageBlobs.map(blob => this.blobToDataURL(blob)));

    } catch (error) {
      console.error('Error fetching outfit images:', error);
      return [];
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

  async startTypingAll(): Promise<void> {
    for (let i = 0; i < this.messages.length; i++) {
      const message = this.messages[i];
      await this.typeMessage(message);
      if (i < this.messages.length - 1) {
        await this.sleep(this.pauseBetweenMessages);
        this.displayedText = '';
      }
    }
  }

  typeMessage(message: string): Promise<void> {
    return new Promise(resolve => {
      let index = 0;
      this.displayedText = '';
      const interval = setInterval(() => {
        this.displayedText += message.charAt(index);
        index++;
        if (index === message.length) {
          clearInterval(interval);
          resolve();
        }
      }, this.typingSpeed);
    });
  }

  sleep(ms: number): Promise<void> {
    return new Promise(resolve => setTimeout(resolve, ms));
  }

  navigateToSignin(): void {
    this.router.navigate(['sign-in']);
  }
}
