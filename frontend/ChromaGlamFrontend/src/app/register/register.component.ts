import { Component } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { firstValueFrom } from 'rxjs';
import {FormsModule} from '@angular/forms';
import {Router} from '@angular/router';
import { UserService } from '../services/user.service';

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
    email: '',
    preferences: '',
    password: '',
    username: ''
  };

  constructor(private http: HttpClient, private router: Router,private userService: UserService) {}

  async register() {
    try {
      const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
      const response = await firstValueFrom(
        this.http.post('http://localhost:8080/api/users/register', this.user, {headers: headers})
      );
      console.log('User registered successfully', response);
      this.userService.setUserData(this.user.username, this.user.preferences);
      this.router.navigate(['home']);
      // You can redirect the user here if you want
    } catch (error) {
      console.error('Registration failed', error);
    }
  }

  navigateToSignin(): void {
    this.router.navigate(['sign-in']);
  }
}
