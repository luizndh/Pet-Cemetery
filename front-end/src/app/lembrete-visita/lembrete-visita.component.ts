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

    constructor(private service: LembreteVisitaService) { }

    abrirModal() {
        this.modalAberto = true;
        this.service.recuperaDadosMeteorologicos(this.dataSelecionada!).subscribe(
            (response) => {
                this.clima = response;
                console.log(this.clima)
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