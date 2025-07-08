import { Component, OnInit } from '@angular/core';
import { Jazigo } from '../../shared/model/jazigo.model';
import { NgFor, NgIf } from '@angular/common';
import { JazigoService } from '../../shared/service/jazigo.service';
import { LocalStorageService } from '../../shared/service/local-storage.service';

@Component({
  selector: 'app-mapa-jazigo',
  imports: [NgFor, NgIf],
  templateUrl: './mapa-jazigo.component.html'
})
export class MapaJazigoComponent implements OnInit {
    jazigos: Jazigo[] = [];

    isAdmin: boolean = false;

    rows: string[] = ['A', 'B', 'C', 'D', 'E', 'F'];
    cols: number[] = Array.from({ length: 12 }, (_, i) => i + 1); // Cria um array de 1 a 12

    constructor(private service: JazigoService, private tokenService: LocalStorageService) {}

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

    comprarJazigo(jazigo: Jazigo) {
        console.log(`Comprar jazigo: ${jazigo.endereco}`);
        this.fecharModal();
    }

    alugarJazigo(jazigo: Jazigo) {
        console.log(`Alugar jazigo: ${jazigo.endereco}`);
        this.fecharModal();
    }
}
