import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JazigoService } from '../../service/jazigo.service';
import { DatePipe, NgIf } from '@angular/common';
import { DetalheJazigo } from './detalhe-jazigo.model';

@Component({
    selector: 'app-detalhar-jazigo',
    imports: [NgIf, DatePipe],
    templateUrl: './detalhar-jazigo.component.html'
})
export class DetalharJazigoComponent implements OnInit {
    jazigoDetalhado?: DetalheJazigo;
    carregando = true;

    constructor(
        private route: ActivatedRoute,
        private jazigoService: JazigoService
    ) { }

    ngOnInit(): void {
        const id = Number(this.route.snapshot.paramMap.get('id'));
        this.jazigoService.detalharJazigo(id).subscribe({
            next: (jazigo) => {
                this.jazigoDetalhado = jazigo;
                this.carregando = false;
            },
            error: () => this.carregando = false
        });
    }

}
