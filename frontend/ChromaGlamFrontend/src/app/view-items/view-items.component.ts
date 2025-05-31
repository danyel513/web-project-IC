import { Component, OnInit  } from '@angular/core';
import { CommonModule } from '@angular/common';
import {Router} from '@angular/router';
import {UserService} from '../services/user.service';
import {HttpClient} from '@angular/common/http';
import { Outfit } from './outfit.model';
import { OutfitDisplay } from './outfitDisplay.model';

@Component({
  standalone: true,
  selector: 'app-view-items',
  imports: [CommonModule],
  templateUrl: './view-items.component.html',
  styleUrls: ['./view-items.component.css']
})
export class ViewItemsComponent implements OnInit{
  username: string = '';
  selectedAvatar: string = '/blue_girl.png';
  avatarPickerVisible: boolean = false;

  // outfit items list
  outfitsToDisplay: OutfitDisplay[] = [];

  avatars: string[] = [
    '/blue_girl.png',
    '/businnesman.png',
    '/girl.png',
    '/man.png',
    '/pink_girl.png'
  ];

  // constructor
  constructor(private router: Router, private userService: UserService,private http: HttpClient) {
    this.username = this.userService.getUsername();
  }

  ngOnInit(): void {
    this.fetchOutfits();
  }


  // items gallery

  fetchOutfits(): void {

    // get all outfits
    this.http.get<Outfit[]>('http://localhost:8080/api/outfits/get/all_outfits').subscribe({
      next: (data) => {

        // get date
        const outfitRequests = data;
        console.log('Outfits fetched:', outfitRequests);

        // get the images for all outfits
        outfitRequests.forEach((outfit) => {
          this.http.get(`http://localhost:8080/api/outfits/get/image?id=${outfit.item_id}`, {
            responseType: 'blob'
          }).subscribe({
            next: (imageBlob) => {
              const imageUrl = URL.createObjectURL(imageBlob);

              this.outfitsToDisplay.push({
                outfit: outfit,
                imageUrl: imageUrl
              });
            },
            error: (err) => {
              console.error('Error loading image for item_id:', outfit.item_id, err);
            }
          });
        });
      },
      error: (err) => {
        console.error('Error fetching outfits:', err);
      }
    });

  }

  // modify availability
  toggleAvailability(item: OutfitDisplay): void {
    // local toggle
    item.outfit.available = item.outfit.available === 1 ? 0 : 1;

    // call endpoint
    this.http.put<boolean>(`http://localhost:8080/api/outfits/updateAvailability?id=${item.outfit.item_id}`, item.outfit.item_id)
      .subscribe({
        next: (response) => {
          console.log('Server response:', response);
        },
        error: (err) => {
          console.error('Error updating availability:', err);
        }
      });
  }

  sortByAvailability(): void {
    this.outfitsToDisplay.sort((a, b) => b.outfit.available - a.outfit.available);
  }
  sortByUnavailability(): void {
    this.outfitsToDisplay.sort((a, b) => - b.outfit.available + a.outfit.available);
  }



  // avatar:

  toggleAvatarPicker(): void {
    this.avatarPickerVisible = !this.avatarPickerVisible;
  }

  selectAvatar(avatar: string): void {
    this.selectedAvatar = avatar;
    this.avatarPickerVisible = false;
  }


  // navigation :

  navigateToSignin(): void {
    this.router.navigate(['sign-in']);
  }
}
