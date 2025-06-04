import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ClienteInadimplente } from './relatorio-inadimplentes/model/cliente-inadimplente.model';
import { Enterro } from './relatorio-enterros/model/enterro.model';
import { Exumacao } from './relatorio-exumacoes/model/exumacao.model';
import { OcupacaoJazigo } from './relatorio-ocupacao/model/ocupacao-jazigo.model';

@Injectable({
    providedIn: 'root'
})
export class RelatorioService {

    private prefixoApi = 'http://localhost:8080/api/admin';

    constructor(private http: HttpClient) { }

    getInadimplentes(): Observable<ClienteInadimplente[]> {
        return this.http.get<ClienteInadimplente[]>(`${this.prefixoApi}/inadimplentes`);
    }

    getUltimosEnterros(): Observable<Enterro[]> {
        return this.http.get<Enterro[]>(`${this.prefixoApi}/enterros`);
    }

    getUltimasExumacoes(): Observable<Exumacao[]> {
        return this.http.get<Exumacao[]>(`${this.prefixoApi}/exumacoes`);
    }

    getOcupacaoJazigos(): Observable<OcupacaoJazigo[]> {
        return this.http.get<OcupacaoJazigo[]>(`${this.prefixoApi}/jazigos`);
    }
}
