import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Servico } from '../model/servico.model';

@Injectable({
  providedIn: 'root'
})
export class ServicosService {

    private prefixoApi: string = 'http://localhost:8080/api/servico'

    constructor(private http: HttpClient) { }

    getPlanos(): Observable<Servico[]> {
        return this.http.get<Servico[]>(`${this.prefixoApi}/planos`);
    }

    getServicosParaOrnamentacao(tipoServico: 'COMPRA' | 'ALUGUEL'): Observable<Servico[]> {
        return this.http.get<Servico[]>(`${this.prefixoApi}/ornamentacao`, {params: {tipoServico}});
    }
}
