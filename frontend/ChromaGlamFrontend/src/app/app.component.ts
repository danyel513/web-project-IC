import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {RegisterComponent} from './register/register.component';
import {RecommendationComponent} from './recommendation/recommendation.component';

@Component({
  selector: 'app-root',
  standalone:true,
  imports: [RouterOutlet,RegisterComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'ChromaGlamFrontend';
}
