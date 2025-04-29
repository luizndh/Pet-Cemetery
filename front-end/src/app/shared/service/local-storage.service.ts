import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {

  private readonly TOKEN_KEY = 'jwt_token';

  constructor() { }

  // Salva o token no Local Storage
  setToken(token: string): void {
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  // Recupera o token do Local Storage
  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  // Remove o token do Local Storage
  removeToken(): void {
    localStorage.removeItem(this.TOKEN_KEY);
  }

  // Verifica se o token existe
  hasToken(): boolean {
    return !!this.getToken();
  }
}