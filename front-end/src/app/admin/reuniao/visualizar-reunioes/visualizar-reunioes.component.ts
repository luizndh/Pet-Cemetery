import { NgFor, AsyncPipe, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { CardReuniaoComponent } from '../card-reuniao/card-reuniao.component';
import { ReuniaoService } from '../../../shared/service/reuniao.service';
import { ReuniaoAdmin } from '../model/reuniao-admin.model';

@Component({
    selector: 'app-visualizar-reunioes',
    imports: [NgFor, CardReuniaoComponent, NgIf],
    templateUrl: './visualizar-reunioes.component.html'
})
export class VisualizarReunioesComponent implements OnInit {
    reunioes: ReuniaoAdmin[] = [];
    carregando = true;

    constructor(private reuniaoService: ReuniaoService) { }

    ngOnInit(): void {
        this.reuniaoService.getReunioesAdmin().subscribe({
            next: (reunioes) => {
                this.reunioes = reunioes;
                this.carregando = false;
            },
            error: () => {
                this.carregando = false;
            }
        });
    }
}
