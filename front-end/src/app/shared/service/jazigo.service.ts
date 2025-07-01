import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Jazigo } from '../model/jazigo.model';
import { HttpClient } from '@angular/common/http';
import { DetalheJazigo } from '../component/detalhar-jazigo/detalhe-jazigo.model';

@Injectable({
    providedIn: 'root'
})
export class JazigoService {

    prefixoApi: string = 'http://localhost:8080/api/jazigo'

    constructor(private http: HttpClient) { }

    getJazigosDoCliente(): Observable<Jazigo[]> {
        return this.http.get<Jazigo[]>(`${this.prefixoApi}/cliente`);
    }

    getMapaJazigos(): Observable<Jazigo[]> {
        return this.http.get<Jazigo[]>(`${this.prefixoApi}/mapa`);
    }

    detalharJazigo(id: number): Observable<DetalheJazigo> {
        return this.http.get<DetalheJazigo>(`${this.prefixoApi}/${id}/detalhe`);
    }

}
