import { Component } from '@angular/core';
import { Jazigo } from '../../shared/model/jazigo.model';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-mapa-jazigo',
  imports: [NgFor],
  templateUrl: './mapa-jazigo.component.html',
  styleUrl: './mapa-jazigo.component.css'
})
export class MapaJazigoComponent {

    // substituir por pegar os jazigos da API
    letras = ['A', 'B', 'C', 'D', 'E', 'F'];
    numeros = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'];

    jazigos: Jazigo[] = [
        {
            endereco: 'A-1',
            idJazigo: 1,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'A-2',
            idJazigo: 2,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'A-3',
            idJazigo: 3,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'A-4',
            idJazigo: 4,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'A-5',
            idJazigo: 5,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'A-6',
            idJazigo: 6,
            mensagem: null,
            foto: null
        }
        ,
        {
            endereco: 'A-7',
            idJazigo: 7,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'A-8',
            idJazigo: 8,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'A-9',
            idJazigo: 9,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'A-10',
            idJazigo: 10,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'A-11',
            idJazigo: 11,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'A-12',
            idJazigo: 12,
            mensagem: null,
            foto: null
        }
        ,
        {
            endereco: 'B-1',
            idJazigo: 13,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'B-2',
            idJazigo: 14,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'B-3',
            idJazigo: 15,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'B-4',
            idJazigo: 16,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'B-5',
            idJazigo: 17,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'B-6',
            idJazigo: 18,
            mensagem: null,
            foto: null
        }
        ,
        {
            endereco: 'B-7',
            idJazigo: 19,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'B-8',
            idJazigo: 20,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'B-9',
            idJazigo: 21,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'B-10',
            idJazigo: 22,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'B-11',
            idJazigo: 23,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'B-12',
            idJazigo: 24,
            mensagem: null,
            foto: null
        }
        ,
        {
            endereco: 'C-1',
            idJazigo: 25,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'C-2',
            idJazigo: 26,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'C-3',
            idJazigo: 27,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'C-4',
            idJazigo: 28,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'C-5',
            idJazigo: 29,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'C-6',
            idJazigo: 30,
            mensagem: null,
            foto: null
        }
        ,
        {
            endereco: 'C-7',
            idJazigo: 31,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'C-8',
            idJazigo: 32,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'C-9',
            idJazigo: 33,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'C-10',
            idJazigo: 34,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'C-11',
            idJazigo: 35,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'C-12',
            idJazigo: 36,
            mensagem: null,
            foto: null
        }
        ,
        {
            endereco: 'D-1',
            idJazigo: 37,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'D-2',
            idJazigo: 38,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'D-3',
            idJazigo: 39,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'D-4',
            idJazigo: 40,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'D-5',
            idJazigo: 41,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'D-6',
            idJazigo: 42,
            mensagem: null,
            foto: null
        }
        ,
        {
            endereco: 'D-7',
            idJazigo: 43,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'D-8',
            idJazigo: 44,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'D-9',
            idJazigo: 45,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'D-10',
            idJazigo: 46,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'D-11',
            idJazigo: 47,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'D-12',
            idJazigo: 48,
            mensagem: null,
            foto: null
        }
        ,
        {
            endereco: 'E-1',
            idJazigo: 49,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'E-2',
            idJazigo: 50,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'E-3',
            idJazigo: 51,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'E-4',
            idJazigo: 52,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'E-5',
            idJazigo: 53,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'E-6',
            idJazigo: 54,
            mensagem: null,
            foto: null
        }
        ,
        {
            endereco: 'E-7',
            idJazigo: 55,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'E-8',
            idJazigo: 56,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'E-9',
            idJazigo: 57,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'E-10',
            idJazigo: 58,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'E-11',
            idJazigo: 59,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'E-12',
            idJazigo: 60,
            mensagem: null,
            foto: null
        }
        ,
        {
            endereco: 'F-1',
            idJazigo: 61,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'F-2',
            idJazigo: 62,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'F-3',
            idJazigo: 63,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'F-4',
            idJazigo: 64,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'F-5',
            idJazigo: 65,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'F-6',
            idJazigo: 66,
            mensagem: null,
            foto: null
        }
        ,
        {
            endereco: 'F-7',
            idJazigo: 67,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'F-8',
            idJazigo: 68,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'F-9',
            idJazigo: 69,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'F-10',
            idJazigo: 70,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'F-11',
            idJazigo: 71,
            mensagem: null,
            foto: null
        },
        {
            endereco: 'F-12',
            idJazigo: 72,
            mensagem: null,
            foto: null
        }
    ]
}
