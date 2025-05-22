import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, Router } from '@angular/router';
import { LocalStorageService } from '../service/local-storage.service';
import { jwtDecode } from 'jwt-decode';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
    constructor(private tokenService: LocalStorageService, private router: Router) { }

    canActivate(route: ActivatedRouteSnapshot): boolean {
        const token = this.tokenService.getToken();
        if (!token) {
            this.router.navigate(['/login']);
            return false;
        }
        try {
            const decoded: any = jwtDecode(token);
            const expectedRole = route.data['role'];

            if (
                decoded.roles &&
                decoded.roles.some((role: any) => role.authority === expectedRole)
            ) {
                return true;
            }
        } catch { }
        this.router.navigate(['/']);
        return false;
    }
}