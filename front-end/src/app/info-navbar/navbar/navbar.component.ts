import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { LocalStorageService } from '../../shared/service/local-storage.service';
import { NgIf } from '@angular/common';
import { jwtDecode } from 'jwt-decode';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink, NgIf],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit{

    usuarioLogado: boolean = false;

    constructor(private tokenService: LocalStorageService, private router: Router) { }

    ngOnInit(): void {
        this.tokenService.usuarioLogado$.subscribe(
            logado => this.usuarioLogado = logado
        );
    }

    logout() {
        this.tokenService.removeToken();
        this.usuarioLogado = false;
        this.router.navigate(['/']);
    }

    handleHome() {
        const token = this.tokenService.getToken();

        if (token === null) {
            this.router.navigate(['/']);
            return;
        }
        // Decodifica o token para pegar as roles
        const decoded: any = jwtDecode(token);

        if (decoded.roles && decoded.roles.some((role: any) => role.authority === "CLIENTE")) {
            this.router.navigate(['/home']);
        } else if (decoded.roles && decoded.roles.some((role: any) => role.authority === "ADMIN")) {
            this.router.navigate(['/admin/home']);
        }
        else {
            this.router.navigate(['/']);
        }
    }

}
