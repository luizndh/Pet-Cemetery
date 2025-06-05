import { Component, OnInit } from '@angular/core';
import { ClienteInadimplente } from './model/cliente-inadimplente.model';
import { RelatorioService } from '../relatorio.service';
import { RelatorioTabelaComponent } from '../relatorio-tabela/relatorio-tabela.component';

@Component({
  selector: 'app-relatorio-inadimplentes',
  imports: [RelatorioTabelaComponent],
  templateUrl: './relatorio-inadimplentes.component.html'
})
export class RelatorioInadimplentesComponent implements OnInit {
    clientes: ClienteInadimplente[] = [];

    // colunas para a tabela
    colunas = [
        { label: 'Nome', campo: 'nome' },
        { label: 'Email', campo: 'email' },
        { label: 'Telefone', campo: 'telefone' },
        { label: 'Desativado', campo: 'desativado' },
        { label: 'Inadimplente', campo: 'inadimplente' }
    ];

  constructor(private relatorioService: RelatorioService) {}

  ngOnInit(): void {
    this.relatorioService.getInadimplentes().subscribe({
      next: (clientes) => {
        this.clientes = clientes;
      }
    });
  }
}
