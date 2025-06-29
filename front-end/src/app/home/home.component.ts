import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { JazigoService } from '../shared/service/jazigo.service';
import { Jazigo } from '../shared/model/jazigo.model';
import { DatePipe, NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-home',
  imports: [RouterLink, NgIf, NgFor, DatePipe],
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit{

    jazigos: Jazigo[] = [];

    constructor(private jazigoService: JazigoService) {
    }

    ngOnInit(): void {
        this.jazigoService.getJazigosDoCliente().subscribe((response: Jazigo[]) => {
            console.log('Jazigos do cliente:', response);

            this.jazigos = response;
        })
    }

}
