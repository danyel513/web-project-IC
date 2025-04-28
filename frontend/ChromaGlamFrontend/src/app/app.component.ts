import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { RouterModule } from '@angular/router';
import {RegisterComponent} from './register/register.component';
import {HomeComponent} from './home/home.component';
import {RecommendationComponent} from './recommendation/recommendation.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet, RegisterComponent, HomeComponent,
    RecommendationComponent, RouterModule
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'ChromaGlamFrontend';
}
