import { Routes } from '@angular/router';
import {RegisterComponent} from './register/register.component';
import {HomeComponent} from './home/home.component';
import {SignInComponent} from './sign-in/sign-in.component';
import {RecommendationComponent} from './recommendation/recommendation.component';

// add here all routes to the components:
export const routes: Routes = [
  { path: '', component: SignInComponent }, // Default route
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomeComponent },
  { path: 'recommendation', component: RecommendationComponent },
  { path: '**', redirectTo: '' }
];
