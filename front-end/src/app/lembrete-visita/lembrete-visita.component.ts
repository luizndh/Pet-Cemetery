import { Component } from '@angular/core';
import { LembreteVisitaService } from './lembrete-visita.service';
import { DatePipe, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Clima } from './model/clima.model';
import { Router } from '@angular/router';

@Component({
    selector: 'app-lembrete-visita',
    templateUrl: './lembrete-visita.component.html',
    imports: [NgIf, FormsModule, DatePipe]
})
export class LembreteVisitaComponent {
    clima?: Clima;
    modalAberto: boolean = false;
    dataSelecionada: Date | null = null;
    carregandoClima: boolean = false;

    minDate: string;
    maxDate: string;

    constructor(private service: LembreteVisitaService, private router: Router) {
        const today = new Date();
        const min = new Date(today);
        min.setDate(today.getDate() + 1);
        const max = new Date(today);
        max.setDate(today.getDate() + 14);

        this.minDate = min.toISOString().split('T')[0];
        this.maxDate = max.toISOString().split('T')[0];
    }

    abrirModal() {
        this.modalAberto = true;
        this.carregandoClima = true;
        this.service.recuperaDadosMeteorologicos(this.dataSelecionada!).subscribe(
            (response) => {
                this.clima = response;
                this.carregandoClima = false;
            },
            () => {
                this.carregandoClima = false;
            }
        );
    }

    agendarLembrete(data: Date) {
        this.service.agendaLembreteVisita(data).subscribe(
            () => {
                this.modalAberto = false;
                this.router.navigate(['/home']);
            }
        );
    }
}