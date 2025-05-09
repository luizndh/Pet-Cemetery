import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { JazigoService } from '../shared/jazigo.service';
import { Jazigo } from '../shared/model/jazigo.model';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-home',
  imports: [RouterLink, NgIf, NgFor],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{

    jazigos: Jazigo[] = [];

    constructor(private jazigoService: JazigoService) {
    }

    ngOnInit(): void {
        this.jazigoService.getJazigosDoCliente().subscribe((response: Jazigo[]) => {
            this.jazigos = response;
        })
    }

}
