import { Component, OnInit } from '@angular/core';
import { Jazigo } from '../../shared/model/jazigo.model';
import { NgFor, NgIf } from '@angular/common';
import { JazigoService } from '../../shared/jazigo.service';

@Component({
  selector: 'app-mapa-jazigo',
  imports: [NgFor, NgIf],
  templateUrl: './mapa-jazigo.component.html',
  styleUrl: './mapa-jazigo.component.css'
})
export class MapaJazigoComponent implements OnInit {
    jazigos: Jazigo[] = [];

    constructor(private service: JazigoService) {}

    ngOnInit(): void {
        this.service.getMapaJazigos().subscribe((response: Jazigo[]) => {
            this.jazigos = response;
            console.log(this.jazigos);
        })
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
