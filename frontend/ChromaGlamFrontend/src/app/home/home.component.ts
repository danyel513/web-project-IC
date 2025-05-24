import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { NgIf, NgFor } from '@angular/common';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  standalone: true,
  styleUrls: ['./home.component.css'],
  imports:[NgIf,NgFor]
})
export class HomeComponent implements OnInit {

  username: string = '';
  preferences: string = '';
  avatars = [
    '/blue_girl.png',
    '/businnesman.png',
    '/girl.png',
    '/man.png',
    '/pink_girl.png'
  ];

  selectedAvatar = this.avatars[0];
  avatarPickerVisible = false;

  constructor(private router: Router, private userService: UserService) {
    this.username = this.userService.getUsername();
    this.preferences = this.userService.getPreferences();
  }

  ngOnInit(): void {
    const savedAvatar = localStorage.getItem('userAvatar');
    if (savedAvatar && this.avatars.includes(savedAvatar)) {
      this.selectedAvatar = savedAvatar;
    }
  }

  toggleAvatarPicker(): void {
    this.avatarPickerVisible = !this.avatarPickerVisible;
  }

  selectAvatar(avatar: string): void {
    this.selectedAvatar = avatar;
    this.avatarPickerVisible = false;
    localStorage.setItem('userAvatar', avatar); // Optional
  }

  tips: string[] = [
    "Don't forget to bring your umbrella with you, there's gonna be rain today in Timișoara.",
    "Wear something light today, it's going to be sunny and warm!",
    "Layer up — it's chilly outside, don't forget a jacket!",
    "Perfect weather for sneakers, leave the boots at home!",
    "It might snow later today, dress warmly and stay safe!"
  ];

  currentTipIndex = 0;

  nextTip() {
    this.currentTipIndex = (this.currentTipIndex + 1) % this.tips.length;
  }

  prevTip() {
    this.currentTipIndex = (this.currentTipIndex - 1 + this.tips.length) % this.tips.length;
  }

  // Existing navigation methods
  navigateToRecommendations(): void {
    this.router.navigate(['recommendations']);
  }

  navigateToSignin(): void {
    this.router.navigate(['sign-in']);
  }

  // NEW: Navigate to View Items page
  navigateToViewItems(): void {
    this.router.navigate(['view-items']);
  }
}
