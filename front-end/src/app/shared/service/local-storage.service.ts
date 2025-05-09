import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';
import { PlatformService } from './platform.service';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {

  private readonly TOKEN_KEY = 'jwt_token';

  constructor(private platformService: PlatformService) { }

  // Salva o token no Local Storage
  setToken(token: string): void {
    if(this.platformService.isBrowser()) {
        localStorage.setItem(this.TOKEN_KEY, token);
    }
  }

  // Recupera o token do Local Storage
  getToken(): string | null {
    if(this.platformService.isBrowser()) {
        return localStorage.getItem(this.TOKEN_KEY);
    }
    return null;
  }

  // Remove o token do Local Storage
  removeToken(): void {
    if(this.platformService.isBrowser()) {
        localStorage.removeItem(this.TOKEN_KEY);
    }
  }

  // Verifica se o token existe
  hasToken(): boolean {
    return !!this.getToken();
  }

}