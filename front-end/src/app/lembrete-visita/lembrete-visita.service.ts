import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LembreteVisitaService {

    private prefixoApi: string = 'http://localhost:8080/api/lembrete-visita'

    constructor(private http: HttpClient) {}

    recuperaDadosMeteorologicos(data: Date): Observable<any> {
        return this.http.get<any>(`${this.prefixoApi}/previsao`, { params: { data: data.toString() } });
    }

    agendaLembreteVisita(data: Date): Observable<any> {
        return this.http.post<any>(`${this.prefixoApi}`, data);
    }
}