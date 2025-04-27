import { Routes } from '@angular/router';
import {RegisterComponent} from './register/register.component';


// add here all routes to the components:
export const routes: Routes = [
  { path: 'register', component: RegisterComponent }, // route to register comp
  { path: '**', redirectTo: '' } // general route

];
