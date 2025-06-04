import { NgFor, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { Exumacao } from './model/exumacao.model';
import { RelatorioService } from '../relatorio.service';

@Component({
  selector: 'app-relatorio-exumacoes',
  imports: [NgIf, NgFor],
  templateUrl: './relatorio-exumacoes.component.html'
})
export class RelatorioExumacoesComponent {
        exumacoes: Exumacao[] = [];
        carregando = true;

        constructor(private relatorioService: RelatorioService) { }

        ngOnInit(): void {
            this.relatorioService.getUltimasExumacoes().subscribe({
                next: (exumacoes) => {
                    this.exumacoes = exumacoes;
                    this.carregando = false;
                },
                error: () => this.carregando = false
            });
        }

        exportarCSV() {
        const csv = [
          ['Jazigo', 'CPF Cliente', 'Nome do Pet', 'Espécie do Pet', 'Data da Exumação'],
          ...this.exumacoes.map(e => [e.jazigo, e.cpf, e.nomePet, e.especie, e.dataExumacao])
        ].map(e => e.join(';')).join('\n');
        const blob = new Blob([csv], { type: 'text/csv' });
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'relatorio-exumacoes.csv';
        a.click();
        window.URL.revokeObjectURL(url);
      }

      exportarPDF() {
        import('jspdf').then(jsPDF => {
          const doc = new jsPDF.jsPDF();
          doc.text('Relatório de Exumações', 10, 10);
          let y = 20;
          this.exumacoes.forEach(e => {
            doc.text(`${e.jazigo} | ${e.cpf} | ${e.nomePet} | ${e.especie} | ${e.dataExumacao}`, 10, y);
            y += 10;
          });
          doc.save('relatorio-exumacoes.pdf');
        });
      }
}
