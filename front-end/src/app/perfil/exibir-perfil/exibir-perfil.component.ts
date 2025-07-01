import { Component, OnInit } from '@angular/core';
import { PerfilService } from '../perfil.service';
import { NgIf } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { LocalStorageService } from '../../shared/service/local-storage.service';
import { DadosPerfil } from '../model/dados-perfil.model';

@Component({
  selector: 'app-exibir-perfil',
  imports: [NgIf, RouterLink],
  templateUrl: './exibir-perfil.component.html'
})
export class ExibirPerfilComponent implements OnInit {

    usuarioLogado?: DadosPerfil;
    modalAberto: boolean = false;

    constructor(private service: PerfilService, private router: Router, private tokenService: LocalStorageService) {}

    ngOnInit(): void {
        this.service.getPerfil().subscribe(
            (response: DadosPerfil) => {
                this.usuarioLogado = response;
            }
        );
    }

    desativarPerfil() {
        this.modalAberto = false;
        this.service.desativarPerfil().subscribe(
            () => {
                this.tokenService.removeToken();
                this.router.navigate(['/']);
            }
        );
    }
}