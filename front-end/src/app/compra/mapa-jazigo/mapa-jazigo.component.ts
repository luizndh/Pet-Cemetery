import { Component, OnInit } from '@angular/core';
import { Jazigo } from '../../shared/model/jazigo.model';
import { NgFor, NgIf } from '@angular/common';
import { JazigoService } from '../../shared/service/jazigo.service';
import { LocalStorageService } from '../../shared/service/local-storage.service';
import { EscolhaJazigo } from '../escolha-jazigo.model';
import { ActivatedRoute, Router } from '@angular/router';
import { ContratacaoStateService } from '../../shared/service/contratacao-state.service';

@Component({
  selector: 'app-mapa-jazigo',
  imports: [NgFor, NgIf],
  templateUrl: './mapa-jazigo.component.html'
})
export class MapaJazigoComponent implements OnInit {
    jazigos: Jazigo[] = [];

    isAdmin: boolean = false;

    rows: string[] = ['A', 'B', 'C', 'D', 'E', 'F'];
    cols: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];

    constructor(
        private service: JazigoService,
        private tokenService: LocalStorageService,
        private router: Router,
        private contratacaoService: ContratacaoStateService,
        private activatedRoute: ActivatedRoute) {}

    ngOnInit(): void {
        this.service.getMapaJazigos().subscribe((response: Jazigo[]) => {
            this.jazigos = response;
        })
        this.tokenService.isAdmin$.subscribe(
            isAdmin => this.isAdmin = isAdmin
        );
    }

    jazigoSelecionado: Jazigo | null = null;

    selecionarJazigo(jazigo: Jazigo) {
        this.jazigoSelecionado = jazigo;
    }

    fecharModal() {
        this.jazigoSelecionado = null;
    }

    selecionarOpcao(jazigo: Jazigo, tipo: 'COMPRA' | 'ALUGUEL') {
        this.fecharModal();

        const escolhaJazigo: EscolhaJazigo = {
            enderecoJazigo: jazigo.endereco,
            idJazigo: jazigo.id,
            compraOuAluguel: { valor: 0, tipoServico: tipo },
            planoEscolhido: null
        };

        this.contratacaoService.setEscolhaJazigo(escolhaJazigo);
        this.router.navigate(['ornamentacao'], { relativeTo: this.activatedRoute });
    }
}