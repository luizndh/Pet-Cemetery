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
            descricao: 'A ONG Paraíso dos Focinhos é uma organização sem fins lucrativos, localizada no Rio de Janeiro, criada há 14 anos, para resgatar, proteger, tratar e cuidar dos animais de rua até que eles consigam uma família. Atualmente temos mais de 500 animais, entre eles, cães, gatos, cavalos, pássaros e até mesmo um porquinho.',
            imagem: 'assets/paraiso-dos-fucinhos.png',
            link: 'https://www.paraisodosfocinhos.com.br/faca-sua-doacao/'
        },
        {
            nome: 'Instituto Caramelo',
            descricao: 'O Instituto Caramelo acredita que todos os animais merecem ter qualidade de vida, e por isso atua com o resgate de animais em situações de risco, promovendo a recuperação e incentivando a adoção. Tão importante quanto oferecer uma nova chance a quem foi maltratado e abandonado, é a conscientização da sociedade sobre a causa animal.',
            imagem: 'assets/instituto-caramelo.png',
            link: 'https://institutocaramelo.org/doe'
        },
        {
            nome: 'Os Indefesos',
            descricao: 'A nossa história começou em 2014, com a nossa fundadora Rosana Guerra, que procurava voluntários para ajudar a resgatar e cuidar de animais indefesos e desesperados por socorro. Assim surgiu o nosso nome, Indefesos, que retrata por quem lutamos: os animais que são indefesos contra a maldade e irresponsabilidade humana.',
            imagem: 'assets/os-indefesos.png',
            link: 'https://www.osindefesos.com.br/doacoes'
        },
        {
            nome: 'Cão sem Dono',
            descricao: 'O Cão Sem Dono é uma ONG (Organização Não Governamental), sem fins lucrativos, e que nasceu de um grande sonho do seu atual presidente: tirar o maior número possível de animais das ruas, dar tratamento adequado e integrá-los a famílias que lhes deem amor, carinho e uma vida digna. Foi criada informalmente em 2005 na cidade de SP.',
            imagem: 'assets/cao-sem-dono.png',
            link: 'https://www.caosemdono.com.br/doacoes'
        },
        {
            nome: 'Cão sem Fome',
            descricao: 'A razão de ser do Projeto Cão sem Fome é contribuir com Protetores independentes que abrigam animais abandonados e resgatados de situações de risco, através de doação de ração e cuidados com a saúde dos animais, oferecendo também suporte e consultoria aos Protetores, para que possam melhorar as condições em que vivem.',
            imagem: 'assets/cao-sem-fome.png',
            link: 'https://projetocaosemfome.com/central-de-doacoes'
        },
        {
            nome: 'Associação Quatro Patinhas',
            descricao: 'Desde a adoção da gatinha Lilo em 2004, a Associação Quatro Patinhas iniciou seu projeto focado em resgatar animais em situação de risco e promover sua saúde. Atualmente, além de cuidar dos animais abrigados, a associação investe em educar a população sobre os direitos dos animais e a posse responsável, além de ser mantida por contribuições.',
            imagem: 'assets/quatro-patinhas.png',
            link: 'https://quatropatinhas.com.br/4P/quero-ajudar/'
        }
    ];
}
