import { Component } from '@angular/core';
import { OcupacaoJazigo } from './model/ocupacao-jazigo.model';
import { RelatorioService } from '../relatorio.service';
import { RelatorioTabelaComponent } from "../relatorio-tabela/relatorio-tabela.component";

@Component({
    selector: 'app-relatorio-ocupacao',
    imports: [RelatorioTabelaComponent],
    templateUrl: './relatorio-ocupacao.component.html'
})
export class RelatorioOcupacaoComponent {
    jazigos: OcupacaoJazigo[] = [];

    // Definindo colunas para o componente de tabela
    colunas = [
        { campo: 'jazigo', label: 'Jazigo' },
        { campo: 'cpf', label: 'CPF' },
        { campo: 'plano', label: 'Plano' },
        { campo: 'pet', label: 'Pet' },
        { campo: 'especie', label: 'Espécie' },
        { campo: 'dataEnterro', label: 'Data de Enterro' }
    ];

    constructor(private relatorioService: RelatorioService) { }

    ngOnInit(): void {
        this.relatorioService.getOcupacaoJazigos().subscribe({
            next: (jazigos) => {
                this.jazigos = jazigos;
            }
        });
    }
}
