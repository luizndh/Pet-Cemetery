import { Component } from '@angular/core';
import { CardHorarioComponent } from '../../shared/component/card-horario/card-horario.component';
import { HorarioFuncionamento } from '../../info-navbar/horario-funcionamento/model/horario-funcionamento.model';
import { NgFor } from '@angular/common';
import { HorarioFuncionamentoService } from '../../shared/service/horario-funcionamento.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-alterar-horario-funcionamento',
    imports: [CardHorarioComponent, NgFor],
    templateUrl: './alterar-horario-funcionamento.component.html',
})
export class AlterarHorarioFuncionamentoComponent {
    horarios: HorarioFuncionamento[] = [];

    constructor(private service: HorarioFuncionamentoService, private router: Router) { }

    ngOnInit(): void {
        this.service.getHorariosFuncionamento().subscribe(
            (horarios: HorarioFuncionamento[]) => {
                this.horarios = horarios;
            }
        );
    }

    salvarAlteracoes() {
        this.service.alterarHorariosFuncionamento(this.horarios).subscribe();
        this.router.navigate(['/admin/home']);
    }
}
