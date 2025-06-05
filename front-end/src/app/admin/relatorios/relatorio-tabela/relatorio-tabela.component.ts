// relatorio-tabela.component.ts
import { NgFor } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-relatorio-tabela',
  templateUrl: './relatorio-tabela.component.html',
  imports: [NgFor]
})
export class RelatorioTabelaComponent {
  @Input() dados: any[] = [];
  @Input() colunas: { campo: string, label: string }[] = [];
  @Input() titulo: string = '';
  @Input() nomeArquivo: string = 'relatorio';

  exportarCSV() {
    const csv = [
      this.colunas.map(c => c.label),
      ...this.dados.map(row => this.colunas.map(c => row[c.campo]))
    ].map(e => e.join(';')).join('\n');
    const blob = new Blob([csv], { type: 'text/csv' });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = `${this.nomeArquivo}.csv`;
    a.click();
    window.URL.revokeObjectURL(url);
  }

  exportarPDF() {
    import('jspdf').then(jsPDF => {
      const doc = new jsPDF.jsPDF();
      doc.text(this.titulo, 10, 10);
      let y = 20;
      this.dados.forEach(row => {
        doc.text(this.colunas.map(c => row[c.campo]).join(' | '), 10, y);
        y += 10;
      });
      doc.save(`${this.nomeArquivo}.pdf`);
    });
  }
}