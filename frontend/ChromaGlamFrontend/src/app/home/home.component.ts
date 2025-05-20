import { Component } from '@angular/core';
import {Router} from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  standalone: true,
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  username: string = '';
  preferences: string = '';


  tips: string[] = [
    "Don't forget to bring your umbrella with you, there's gonna be rain today in Timișoara.",
    "Wear something light today, it's going to be sunny and warm!",
    "Layer up — it's chilly outside, don't forget a jacket!",
    "Perfect weather for sneakers, leave the boots at home!",
    "It might snow later today, dress warmly and stay safe!"
  ];
  constructor(private router: Router, private userService: UserService) {
    this.username = this.userService.getUsername();
    this.preferences = this.userService.getPreferences();
  } // inject Router and Service

  navigateToRecommendations(): void {
    this.router.navigate(['recommendations']);

  }

  navigateToSignin(): void {
    this.router.navigate(['sign-in']);
  }

  currentTipIndex = 0;

  nextTip() {
    this.currentTipIndex = (this.currentTipIndex + 1) % this.tips.length;
  }

  prevTip() {
    this.currentTipIndex = (this.currentTipIndex - 1 + this.tips.length) % this.tips.length;
  }
}
