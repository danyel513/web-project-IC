import { Routes } from '@angular/router';
import {RegisterComponent} from './register/register.component';
import {SignInComponent} from './sign-in/sign-in.component';
import {RecommendationComponent} from './recommendation/recommendation.component';
import {HomeComponent} from './home/home.component';
import {ViewItemsComponent} from './view-items/view-items.component';

// add here all routes to the components:
export const routes: Routes = [
  { path: '', component: SignInComponent }, // default route to sign-in comp
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomeComponent },
  { path: 'recommendations', component: RecommendationComponent},
  { path: 'view-items', component:ViewItemsComponent},
  { path: '**', redirectTo: '' } // general route
];
