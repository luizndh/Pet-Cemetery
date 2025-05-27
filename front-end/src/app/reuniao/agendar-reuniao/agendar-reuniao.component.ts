import { DatePipe, NgFor, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ReuniaoService } from '../../shared/service/reuniao.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-agendar-reuniao',
    imports: [DatePipe, NgFor, NgIf, ReactiveFormsModule, FormsModule],
    templateUrl: './agendar-reuniao.component.html'
})
export class AgendarReuniaoComponent {

    motivos = ['Avaliação de jazigo', 'Negociação', 'Visita guiada', 'Assuntos administrativos'];
    horarios = ['08:00', '09:00', '10:00', '11:00', '12:00'];
    dataSelecionada!: Date;
    motivoSelecionado!: string;
    horarioSelecionado!: string;
    modalAberto = false;
    minDate: string;
    maxDate: string;

    agendandoReuniao: boolean = false;

    constructor(private reuniaoService: ReuniaoService, private router: Router) {
        const today = new Date();
        const min = new Date(today);
        min.setDate(today.getDate() + 2);
        const max = new Date(today);
        max.setDate(today.getDate() + 30);
        this.minDate = min.toISOString().split('T')[0];
        this.maxDate = max.toISOString().split('T')[0];
    }

    abrirModal() {
        this.modalAberto = true;
    }

    confirmarReuniao() {
        this.agendandoReuniao = true;
        this.reuniaoService.agendarReuniao({data: this.dataSelecionada, hora: this.horarioSelecionado, assunto: this.motivoSelecionado}).subscribe(
            () => {
                this.modalAberto = false;
                this.agendandoReuniao = false;
                this.router.navigate(['/home']);
            },
            () => {
                this.modalAberto = false;
                this.agendandoReuniao = false;
            }
        )

    }

}
