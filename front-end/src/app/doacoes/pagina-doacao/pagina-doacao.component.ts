import { Component } from '@angular/core';
import { CartaoDoacaoComponent } from '../cartao-doacao/cartao-doacao.component';
import { Organizacao } from '../model/organizacao.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-pagina-doacao',
  imports: [CartaoDoacaoComponent, CommonModule],
  templateUrl: './pagina-doacao.component.html',
  styleUrl: './pagina-doacao.component.css'
})
export class PaginaDoacaoComponent {

    organizacoes: Organizacao[] = [
        {
            nome: 'Paraíso dos Focinhos',
            descricao: 'Descrição da Organização 1',
            imagem: 'https://via.placeholder.com/150',
            link: 'https://www.paraisodosfocinhos.com.br/faca-sua-doacao/'
        },
        {
            nome: 'Instituto Caramelo',
            descricao: 'Descrição da Organização 2',
            imagem: 'https://via.placeholder.com/150',
            link: 'https://institutocaramelo.org/doe'
        },
        {
            nome: 'Os Indefesos',
            descricao: 'Descrição da Organização 3',
            imagem: 'https://via.placeholder.com/150',
            link: 'https://www.osindefesos.com.br/doacoes'
        },
        {
            nome: 'Cão sem Dono',
            descricao: 'Descrição da Organização 3',
            imagem: 'https://via.placeholder.com/150',
            link: 'https://www.caosemdono.com.br/doacoes'
        },
        {
            nome: 'Cão sem Fome',
            descricao: 'Descrição da Organização 3',
            imagem: 'https://via.placeholder.com/150',
            link: 'https://projetocaosemfome.com/central-de-doacoes'
        },
        {
            nome: 'Associação Quatro Patinhas',
            descricao: 'Descrição da Organização 3',
            imagem: 'https://via.placeholder.com/150',
            link: 'https://quatropatinhas.com.br/4P/quero-ajudar/'
        }
    ];
}
