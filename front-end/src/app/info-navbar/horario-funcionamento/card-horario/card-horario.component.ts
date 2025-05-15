import { NgIf } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-card-horario',
  imports: [NgIf],
  templateUrl: './card-horario.component.html',
})
export class CardHorarioComponent {
    @Input() diaSemana!: string;
    @Input() abertura!: string;
    @Input() fechamento!: string;
    @Input() fechado!: boolean;
}
