import { Component, OnInit } from '@angular/core';
import { CardHorarioComponent } from '../../../shared/component/card-horario/card-horario.component';
import { HorarioFuncionamento } from '../model/horario-funcionamento.model';
import { NgFor } from '@angular/common';
import { HorarioFuncionamentoService } from '../../../shared/service/horario-funcionamento.service';
@Component({
  selector: 'app-pagina-horario',
  imports: [CardHorarioComponent, NgFor],
  templateUrl: './pagina-horario.component.html',
})
export class PaginaHorarioComponent implements OnInit {
    horarios: HorarioFuncionamento[] = [];

    constructor(private service: HorarioFuncionamentoService) { }

  ngOnInit(): void {
    this.service.getHorariosFuncionamento().subscribe(
      (horarios: HorarioFuncionamento[]) => {
        this.horarios = horarios;
      }
    );
  }
}
