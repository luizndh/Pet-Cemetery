import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AgendamentoReuniao } from './model/agendamento-reuniao.model';

@Injectable({
  providedIn: 'root'
})
export class ReuniaoService {

    private prefixoApi: string = 'http://localhost:8080/api/reuniao'

    constructor(private http: HttpClient) { }

    agendarReuniao(dadosReuniao: AgendamentoReuniao) {
        return this.http.post<boolean>(`${this.prefixoApi}`, dadosReuniao);
    }
}