import { NgIf } from '@angular/common';
import { Component, Input, Output, EventEmitter } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
    selector: 'app-card-horario',
    templateUrl: './card-horario.component.html',
    imports: [NgIf, ReactiveFormsModule, FormsModule]
})
export class CardHorarioComponent {
    @Input() diaSemana!: string;
    @Input() abertura!: string;
    @Output() aberturaChange = new EventEmitter<string>();

    @Input() fechamento!: string;
    @Output() fechamentoChange = new EventEmitter<string>();

    @Input() fechado!: boolean;
    @Output() fechadoChange = new EventEmitter<boolean>();
    @Input() editavel: boolean = false;


    onAberturaChange(novoValor: string) {
        this.aberturaChange.emit(novoValor);
    }

    onFechamentoChange(novoValor: string) {
        this.fechamentoChange.emit(novoValor);
    }

    onFechadoChange(novoValor: boolean) {
        this.fechadoChange.emit(novoValor);
    }
}