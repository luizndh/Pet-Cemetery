import { Component, OnInit } from '@angular/core';
import { ClienteInadimplente } from './model/cliente-inadimplente.model';
import { RelatorioService } from '../relatorio.service';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-relatorio-inadimplentes',
  imports: [NgFor, NgIf],
  templateUrl: './relatorio-inadimplentes.component.html'
})
export class RelatorioInadimplentesComponent implements OnInit {
    clientes: ClienteInadimplente[] = [];
  carregando = true;

  constructor(private relatorioService: RelatorioService) {}

  ngOnInit(): void {
    this.relatorioService.getInadimplentes().subscribe({
      next: (clientes) => {
        this.clientes = clientes;
        this.carregando = false;
      },
      error: () => this.carregando = false
    });
  }

  exportarCSV() {
    const csv = [
      ['Nome', 'Email', 'Telefone', 'Desativado', 'Inadimplente'],
      ...this.clientes.map(c => [c.nome, c.email, c.telefone, c.desativado ? 'Sim' : 'Não', c.inadimplente ? 'Sim' : 'Não'])
    ].map(e => e.join(';')).join('\n');
    const blob = new Blob([csv], { type: 'text/csv' });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'relatorio-inadimplentes.csv';
    a.click();
    window.URL.revokeObjectURL(url);
  }

  exportarPDF() {
    // Use jsPDF ou outra lib para gerar PDF (exemplo básico)
    import('jspdf').then(jsPDF => {
      const doc = new jsPDF.jsPDF();
      doc.text('Relatório de Inadimplentes', 10, 10);
      let y = 20;
      this.clientes.forEach(c => {
        doc.text(`${c.nome} | ${c.email} | ${c.telefone} | ${c.desativado ? 'Desativado' : ''} | ${c.inadimplente ? 'Inadimplente' : ''}`, 10, y);
        y += 10;
      });
      doc.save('relatorio-inadimplentes.pdf');
    });
  }
}
