import { Injectable } from '@angular/core';

const TOKEN = 'token';
const USER = 'user';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }

  private static isWindowDefined(): boolean {
    return typeof window !== 'undefined';
  }

  static saveToken(token: string): void {
    if (this.isWindowDefined()) {
      window.localStorage.removeItem(TOKEN);
      window.localStorage.setItem(TOKEN, token);
    }
  }

  static saveUser(user: any): void {
    if (this.isWindowDefined()) {
      window.localStorage.removeItem(USER);
      window.localStorage.setItem(USER, JSON.stringify(user));
    }
  }

  static getUserId(): string {
    const user = this.getUser();
  
    if (user == null) {
      return '';
    }
  
    return user.id;
  }

  static getToken(): string | null {
    return this.isWindowDefined() ? window.localStorage.getItem(TOKEN) : null;
  }

  static getUser(): any | null {
    if (!this.isWindowDefined()) return null;
    
    const user = window.localStorage.getItem(USER);
    return user ? JSON.parse(user) : null;  // Handle null case before parsing
  }

  static getUserRole(): string {
    const user = this.getUser();
    return user ? user.role : '';
  }

  static isAdminLoggedIn(): boolean {
    if (this.getToken() == null) return false;

    const role: string = this.getUserRole();
    return role === "ADMIN";
  }

  static isCustomerLoggedIn(): boolean {
    if (this.getToken() == null) return false;

    const role: string = this.getUserRole();
    return role === "CUSTOMER";
  }

  static logout(): void {
    if (this.isWindowDefined()) {
      window.localStorage.removeItem(TOKEN);
      window.localStorage.removeItem(USER);
    }
  }
}
