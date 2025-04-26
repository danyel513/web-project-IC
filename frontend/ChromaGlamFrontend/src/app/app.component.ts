import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
//import {RegisterComponent} from './register/register.component';
import {RecommendationComponent} from './recommendation/recommendation.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet,RecommendationComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'ChromaGlamFrontend';
}
