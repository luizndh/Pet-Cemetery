import { Component, OnInit } from '@angular/core';
import { ContratacaoStateService } from '../../shared/service/contratacao-state.service';
import { EscolhaJazigo } from '../escolha-jazigo.model';
import { Servico } from '../../shared/model/servico.model';
import { ServicosService } from '../../shared/service/servicos.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-ornamentacao',
  imports: [CommonModule],
  templateUrl: './ornamentacao.component.html'
})
export class OrnamentacaoComponent implements OnInit {

    escolhaRecebida: EscolhaJazigo | null = null;
    planos: Servico[] = [];
    planoSelecionado: Servico | null = null;
    valorTotal: number = 0;

    constructor(private contratacaoService: ContratacaoStateService, private servicosService: ServicosService) {}

    ngOnInit(): void {
        this.contratacaoService.escolhaJazigo$.subscribe(escolha => {
            if (escolha) {
                this.escolhaRecebida = escolha;
            }
        });

        this.servicosService.getServicosParaOrnamentacao(this.escolhaRecebida!.compraOuAluguel.tipoServico as 'COMPRA' | 'ALUGUEL').subscribe((response: Servico[]) => {
            response.forEach(servico => {
                if(servico.tipoServico === this.escolhaRecebida!.compraOuAluguel.tipoServico) {
                    this.escolhaRecebida!.compraOuAluguel = servico;
                } else {
                    this.planos.push(servico);
                }
            });
            this.planoSelecionado = this.planos[0];
            this.calcularValorTotal();
        });
    }

    selecionarPlano(plano: Servico) {
        this.planoSelecionado = plano;
        this.calcularValorTotal();
    }

    calcularValorTotal(): void {
        if (this.planoSelecionado) {
            this.valorTotal = this.escolhaRecebida!.compraOuAluguel.valor + this.planoSelecionado.valor;
        } else {
            this.valorTotal = this.escolhaRecebida!.compraOuAluguel.valor;
        }
    }
}
