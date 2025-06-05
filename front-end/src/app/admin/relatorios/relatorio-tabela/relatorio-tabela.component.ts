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
  Promise.all([
    import('jspdf'),
    import('jspdf-autotable')
  ]).then(([jsPDFModule, autoTableModule]) => {
    const jsPDF = jsPDFModule.jsPDF;
    const autoTable = autoTableModule.default;

    const doc = new jsPDF({
      orientation: this.colunas.length > 6 ? 'landscape' : 'portrait',
      unit: 'pt',
      format: 'a4'
    });

    doc.setFontSize(16);
    doc.text(this.titulo, doc.internal.pageSize.getWidth() / 2, 40, { align: 'center' });

    autoTable(doc, {
      head: [this.colunas.map(c => c.label)],
      body: this.dados.map(row => this.colunas.map(c => row[c.campo])),
      startY: 60,
      styles: { fontSize: 10, halign: 'center' },
      headStyles: { fillColor: [55, 65, 81] }, // Tailwind neutral-800
      margin: { left: 20, right: 20 }
    });

    doc.save(`${this.nomeArquivo}.pdf`);
  });
}
}