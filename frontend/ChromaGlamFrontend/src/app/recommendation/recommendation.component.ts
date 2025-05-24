import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UserService} from '../services/user.service';

@Component({
  selector: 'app-recommendation',
  imports: [],
  templateUrl: './recommendation.component.html',
  standalone: true,
  styleUrl: './recommendation.component.css'
})
export class RecommendationComponent {
  username : string = "";

  constructor(private http: HttpClient,private userService: UserService) {
    this.username = this.userService.getUsername();
  }

  // get the recommendation given by the AI model
  getOutfitRecommendation(): void {
    let result:string = "";
    let outfitIds: number[] = [];

    this.http.get(`http://localhost:8080/api/outfits/get/outfit?username=${this.username}`, { responseType: 'text' })
      .subscribe({
        next: (response: string) => {

          result = response; // response pattern: id1/id2/...
          console.log("Raw response:", result);

          // split by "/" and convert each to number
          // and save a list of int containing each id
          outfitIds = result
            .split('/')
            .map(id => parseInt(id))
            .filter(id => !isNaN(id));

          console.log("Parsed outfit IDs:", outfitIds);
        },
        error: (err) => {
          console.error('Error fetching outfit recommendation:', err);
        }
      });

    // call the backend using the ID of outfit items to obtain the images

  }
}
