import { Injectable } from '@angular/core';
import { PlatformService } from './platform.service';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {

  private readonly TOKEN_KEY = 'jwt_token';

  // Observable para o estado de login
  private usuarioLogadoSubject: BehaviorSubject<boolean>;
  usuarioLogado$;

  constructor(private platformService: PlatformService) {
    this.usuarioLogadoSubject = new BehaviorSubject<boolean>(this.hasToken());
    this.usuarioLogado$ = this.usuarioLogadoSubject.asObservable();
   }

  // Salva o token no Local Storage
  setToken(token: string): void {
    if(this.platformService.isBrowser()) {
        localStorage.setItem(this.TOKEN_KEY, token);
        this.usuarioLogadoSubject.next(true);
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
        this.usuarioLogadoSubject.next(false);
    }
  }

  // Verifica se o token existe
  hasToken(): boolean {
    return !!this.getToken();
  }

}