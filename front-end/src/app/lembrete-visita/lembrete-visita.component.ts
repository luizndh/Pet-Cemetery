import { Component } from '@angular/core';
import { LembreteVisitaService } from './lembrete-visita.service';
import { DatePipe, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
    selector: 'app-lembrete-visita',
    templateUrl: './lembrete-visita.component.html',
    styleUrls: ['./lembrete-visita.component.css'],
    imports: [NgIf, FormsModule, DatePipe]
})
export class LembreteVisitaComponent {
    weather: { temp: number; description: string; humidity: number } | null = null;
    modalAberto: boolean = false;
    dataSelecionada!: Date;

    constructor(private service: LembreteVisitaService) { }

    abrirModal() {
        this.modalAberto = true;
        this.service.recuperaDadosMeteorologicos(this.dataSelecionada!).subscribe(
            (response) => {
                console.log('Dados meteorológicos recebidos:', response);
                this.weather = {
                    temp: response.main.temp,
                    description: response.weather[0].description,
                    humidity: response.main.humidity
                };
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