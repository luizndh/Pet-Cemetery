import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Clima } from './model/clima.model';

@Injectable({
  providedIn: 'root'
})
export class LembreteVisitaService {

    private prefixoApi: string = 'http://localhost:8080/api'

    constructor(private http: HttpClient) {}

    recuperaDadosMeteorologicos(data: Date): Observable<Clima> {
        return this.http.get<Clima>(`${this.prefixoApi}/weather/previsao`, { params: { data: data.toString() } });
    }

    agendaLembreteVisita(data: Date): Observable<any> {
        return this.http.post<any>(`${this.prefixoApi}/cliente/lembrerte`, data);
    }
}