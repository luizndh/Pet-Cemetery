import { Component } from '@angular/core';
import { RelatorioService } from '../relatorio.service';
import { Enterro } from './model/enterro.model';
import { RelatorioTabelaComponent } from '../relatorio-tabela/relatorio-tabela.component';

@Component({
    selector: 'app-relatorio-enterros',
    imports: [RelatorioTabelaComponent],
    templateUrl: './relatorio-enterros.component.html'
})
export class RelatorioEnterrosComponent {
    enterros: Enterro[] = [];
    // colunas para a tabela
    colunas = [
        { campo: 'jazigo', label: 'Jazigo' },
        { campo: 'cpf', label: 'CPF Cliente' },
        { campo: 'nomePet', label: 'Nome do Pet' },
        { campo: 'especie', label: 'Espécie do Pet' },
        { campo: 'dataEnterro', label: 'Data do Enterro' }
    ];

    constructor(private relatorioService: RelatorioService) { }

    ngOnInit(): void {
        this.relatorioService.getUltimosEnterros().subscribe({
            next: (enterros) => {
                this.enterros = enterros;
            }
        });
    }
}
