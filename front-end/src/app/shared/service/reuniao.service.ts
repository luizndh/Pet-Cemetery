import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AgendamentoReuniao } from '../../reuniao/model/agendamento-reuniao.model';
import { ReuniaoAdmin } from '../../admin/reuniao/model/reuniao-admin.model';

@Injectable({
  providedIn: 'root'
})
export class ReuniaoService {

    private prefixoApi: string = 'http://localhost:8080/api/reuniao'

    constructor(private http: HttpClient) { }

    agendarReuniao(dadosReuniao: AgendamentoReuniao) {
        return this.http.post<boolean>(`${this.prefixoApi}`, dadosReuniao);
    }

    getReunioesAdmin() {
        return this.http.get<ReuniaoAdmin[]>(`${this.prefixoApi}/admin/visualizar`);
    }
}