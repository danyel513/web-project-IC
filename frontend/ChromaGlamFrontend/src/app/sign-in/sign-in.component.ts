import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';

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

  constructor(private http: HttpClient) {}

  async signIn() {
    const userCredentials = {
      username: this.username,
      password: this.password,
    };

    try {
      const response = await firstValueFrom(this.http.post('http://localhost:8080/api/users/login', userCredentials));
      console.log('User logged in successfully', response);
      // Optionally, redirect the user to another page after login
      // this.router.navigate(['/home']);
    } catch (error) {
      console.error('Login failed', error);
      this.error = 'Login failed. Please check your credentials and try again.';
    }
  }
}
