import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private username: string = '';
  private preferences: string = '';

  setUserData(username: string, preferences: string): void {
    this.username = username;
    this.preferences = preferences;
  }

  getUsername(): string {
    return this.username;
  }

  getPreferences(): string {
    return this.preferences;
  }
}
