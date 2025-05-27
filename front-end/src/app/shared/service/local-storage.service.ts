import { Injectable } from '@angular/core';
import { PlatformService } from './platform.service';
import { BehaviorSubject } from 'rxjs';
import { jwtDecode } from 'jwt-decode';

@Injectable({
    providedIn: 'root'
})
export class LocalStorageService {

    private readonly TOKEN_KEY = 'jwt_token';

    // Observable para o estado de login
    private usuarioLogadoSubject: BehaviorSubject<boolean>;
    usuarioLogado$;

    private isAdminSubject = new BehaviorSubject<boolean>(this.isUsuarioAdmin());
    isAdmin$ = this.isAdminSubject.asObservable();

    private isClienteSubject = new BehaviorSubject<boolean>(this.isUsuarioCliente());
    isCliente$ = this.isClienteSubject.asObservable();


    constructor(private platformService: PlatformService) {
        this.usuarioLogadoSubject = new BehaviorSubject<boolean>(this.hasToken());
        this.usuarioLogado$ = this.usuarioLogadoSubject.asObservable();
    }

    setAdmin(isAdmin: boolean) {
        this.isAdminSubject.next(isAdmin);
    }

    // Salva o token no Local Storage
    setToken(token: string): void {
        if (this.platformService.isBrowser()) {
            localStorage.setItem(this.TOKEN_KEY, token);
            this.usuarioLogadoSubject.next(true);
            this.isAdminSubject.next(this.isUsuarioAdmin(token));
            this.isClienteSubject.next(this.isUsuarioCliente(token));
        }
    }

    // Recupera o token do Local Storage
    getToken(): string | null {
        if (this.platformService.isBrowser()) {
            return localStorage.getItem(this.TOKEN_KEY);
        }
        return null;
    }

    // Remove o token do Local Storage
    removeToken(): void {
        if (this.platformService.isBrowser()) {
            localStorage.removeItem(this.TOKEN_KEY);
            this.usuarioLogadoSubject.next(false);
            this.isAdminSubject.next(false);
            this.isClienteSubject.next(false);
        }
    }

    // Verifica se o token existe
    hasToken(): boolean {
        return !!this.getToken();
    }

    // Método para verificar se o token (ou o atual) é de admin
    isUsuarioAdmin(token?: string): boolean {
        const jwt = token
        if (!jwt) return false;
        try {
            const decoded: any = jwtDecode(jwt);
            if (decoded.roles && decoded.roles.some((role: any) => role.authority === "ADMIN")) {
                return true;
            }
        } catch {
            return false;
        }
        return false;
    }

    // Método para verificar se o token (ou o atual) é de admin
    isUsuarioCliente(token?: string): boolean {
        const jwt = token
        if (!jwt) return false;
        try {
            const decoded: any = jwtDecode(jwt);
            if (decoded.roles && decoded.roles.some((role: any) => role.authority === "CLIENTE")) {
                return true;
            }
        } catch {
            return false;
        }
        return false;
    }

}