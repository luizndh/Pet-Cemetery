import { Component } from '@angular/core';
import { Exumacao } from './model/exumacao.model';
import { RelatorioService } from '../relatorio.service';
import { RelatorioTabelaComponent } from '../relatorio-tabela/relatorio-tabela.component';

@Component({
  selector: 'app-relatorio-exumacoes',
  imports: [RelatorioTabelaComponent],
  templateUrl: './relatorio-exumacoes.component.html'
})
export class RelatorioExumacoesComponent {
        exumacoes: Exumacao[] = [];
        // colunas para a tabela
        colunas = [
            { campo: 'jazigo', label: 'Jazigo' },
            { campo: 'cpf', label: 'CPF Cliente' },
            { campo: 'nomePet', label: 'Nome do Pet' },
            { campo: 'especie', label: 'Espécie do Pet' },
            { campo: 'dataExumacao', label: 'Data da Exumação' }
        ];

        constructor(private relatorioService: RelatorioService) { }

        ngOnInit(): void {
            this.relatorioService.getUltimasExumacoes().subscribe({
                next: (exumacoes) => {
                    this.exumacoes = exumacoes;
                }
            });
        }
}
