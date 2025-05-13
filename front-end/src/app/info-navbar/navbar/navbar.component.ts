import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { LocalStorageService } from '../../shared/service/local-storage.service';
import { NgIf } from '@angular/common';

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
}
