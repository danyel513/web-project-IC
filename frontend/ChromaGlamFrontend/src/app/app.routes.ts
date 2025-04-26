import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './register/register.component'; // <-- import your RegisterComponent
import { RecommendationComponent } from './recommendation/recommendation.component';


export const routes: Routes = [
  { path: '', redirectTo: '/register', pathMatch: 'full' }, // default redirect
 // { path: 'register', component: RegisterComponent },
  { path: 'recommendations', component: RecommendationComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
