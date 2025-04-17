import { Component, Input } from '@angular/core';
import { Organizacao } from '../model/organizacao.model';

@Component({
  selector: 'app-cartao-doacao',
  imports: [],
  templateUrl: './cartao-doacao.component.html',
  styleUrl: './cartao-doacao.component.css'
})
export class CartaoDoacaoComponent {

    @Input() org!: Organizacao;
}
