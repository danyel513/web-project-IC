import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-register',
  standalone: true,
  imports:[FormsModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  user = {
    name: '',
    username: '',
    email: '',
    preferences: '',
    password: ''
  };

  constructor(private http: HttpClient) {}

  async register() {
    try {
      const response = await firstValueFrom(
        this.http.post('http://localhost:8080/api/users/register', this.user)
      );
      console.log('User registered successfully', response);
      // You can redirect the user here if you want
    } catch (error) {
      console.error('Registration failed', error);
    }
  }
}
