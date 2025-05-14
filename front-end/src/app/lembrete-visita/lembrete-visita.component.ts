import { Component } from '@angular/core';
import { LembreteVisitaService } from './lembrete-visita.service';
import { DatePipe, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Clima } from './model/clima.model';

@Component({
    selector: 'app-lembrete-visita',
    templateUrl: './lembrete-visita.component.html',
    imports: [NgIf, FormsModule, DatePipe]
})
export class LembreteVisitaComponent {
    clima?: Clima;
    modalAberto: boolean = false;
    dataSelecionada!: Date;
    carregandoClima: boolean = false;

    minDate: string;
    maxDate: string;

    constructor(private service: LembreteVisitaService) {
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
            (error) => {
                this.carregandoClima = false;
            }
        );
    }

    agendarLembrete(data: Date) {
        console.log('Agendando lembrete para a data:', data);
        this.service.agendaLembreteVisita(data).subscribe(
            (response) => {
                console.log('Lembrete agendado com sucesso!', response);
                this.modalAberto = false;
            }
        );
    }
}