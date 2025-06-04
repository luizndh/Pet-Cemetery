import { Component } from '@angular/core';
import { RelatorioService } from '../relatorio.service';
import { Enterro } from './model/enterro.model';
import { NgFor, NgIf } from '@angular/common';

@Component({
    selector: 'app-relatorio-enterros',
    imports: [NgIf, NgFor],
    templateUrl: './relatorio-enterros.component.html'
})
export class RelatorioEnterrosComponent {
    enterros: Enterro[] = [];
    carregando = true;

    constructor(private relatorioService: RelatorioService) { }

    ngOnInit(): void {
        this.relatorioService.getUltimosEnterros().subscribe({
            next: (enterros) => {
                this.enterros = enterros;
                this.carregando = false;
            },
            error: () => this.carregando = false
        });
    }

    exportarCSV() {
        const csv = [
            ['Jazigo', 'CPF Cliente', 'Nome do Pet', 'Espécie do Pet', 'Data do Enterro'],
            ...this.enterros.map(e => [e.jazigo, e.cpf, e.nomePet, e.especie, e.dataEnterro])
        ].map(e => e.join(';')).join('\n');
        const blob = new Blob([csv], { type: 'text/csv' });
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'relatorio-enterros.csv';
        a.click();
        window.URL.revokeObjectURL(url);
    }

    exportarPDF() {
        import('jspdf').then(jsPDF => {
            const doc = new jsPDF.jsPDF();
            doc.text('Relatório de Enterros', 10, 10);
            let y = 20;
            this.enterros.forEach(e => {
                doc.text(`${e.jazigo} | ${e.cpf} | ${e.nomePet} | ${e.especie} | ${e.dataEnterro}`, 10, y);
                y += 10;
            });
            doc.save('relatorio-enterros.pdf');
        });
    }
}
