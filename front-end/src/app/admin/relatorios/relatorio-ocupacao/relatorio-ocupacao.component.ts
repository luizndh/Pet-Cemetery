import { NgFor, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { OcupacaoJazigo } from './model/ocupacao-jazigo.model';
import { RelatorioService } from '../relatorio.service';

@Component({
  selector: 'app-relatorio-ocupacao',
  imports: [NgIf, NgFor],
  templateUrl: './relatorio-ocupacao.component.html'
})
export class RelatorioOcupacaoComponent {
  jazigos: OcupacaoJazigo[] = [];
  carregando = true;

  constructor(private relatorioService: RelatorioService) {}

  ngOnInit(): void {
    this.relatorioService.getOcupacaoJazigos().subscribe({
      next: (jazigos) => {
        console.log("Dados recebidos:", jazigos);

        this.jazigos = jazigos;
        this.carregando = false;
      },
      error: () => this.carregando = false
    });
  }

  exportarCSV() {
    const csv = [
      ['Jazigo', 'CPF', 'Plano', 'Pet', 'Espécie', 'Data de Enterro'],
      ...this.jazigos.map(j => [j.jazigo, j.cpf, j.plano, j.pet, j.especie, j.dataEnterro])
    ].map(e => e.join(';')).join('\n');
    const blob = new Blob([csv], { type: 'text/csv' });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'relatorio-ocupacao.csv';
    a.click();
    window.URL.revokeObjectURL(url);
  }

  exportarPDF() {
    // Use jsPDF ou outra lib para gerar PDF (exemplo básico)
    import('jspdf').then(jsPDF => {
      const doc = new jsPDF.jsPDF();
      doc.text('Relatório de Ocupação de Jazigos', 10, 10);
      let y = 20;
      this.jazigos.forEach(j => {
        doc.text(`${j.jazigo} | ${j.cpf} | ${j.plano} | ${j.pet} | ${j.especie} | ${j.dataEnterro}`, 10, y);
        y += 10;
      });
      doc.save('relatorio-ocupacao.pdf');
    });
  }
}
