import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { firstValueFrom } from 'rxjs';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service'; // Import your service


@Component({
  selector: 'app-sign-in',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss'],
})
export class SignInComponent {
  username = '';
  password = '';
  error = '';

  constructor(private http: HttpClient, private router: Router, private userService: UserService) {}

  async signIn() {
    const userCredentials = {
      username: this.username,
      password: this.password
    };

    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    try {
      console.log(userCredentials.username);
      console.log(userCredentials.password);
      const response: any = await firstValueFrom(
        this.http.post(
          'http://localhost:8080/api/users/login',
          userCredentials,
          { headers: headers }
        )
      );
      console.log('User logged in successfully', response);
      // Store user data in the service
      this.userService.setUserData(this.username,response.preferences);
      // Optionally, redirect the user to another page after login
      this.router.navigate(['/home']);
    } catch (error) {
      console.error('Login failed', error);
      this.error = 'Login failed. Please check your credentials and try again.';
    }
  }

  navigateToRegister(): void{
    this.router.navigate(['register']);
  }
}
