import { Routes } from '@angular/router';
import {RegisterComponent} from './register/register.component';
import {SignInComponent} from './sign-in/sign-in.component';
import {RecommendationComponent} from './recommendation/recommendation.component';
import {HomeComponent} from './home/home.component';

// add here all routes to the components:
export const routes: Routes = [
  { path: '', component: SignInComponent }, // default route to home comp
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomeComponent },
  { path: 'recommendations', component: RecommendationComponent},
  { path: '**', redirectTo: '' } // general route

];
