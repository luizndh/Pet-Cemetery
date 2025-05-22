import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HorarioFuncionamento } from '../../info-navbar/horario-funcionamento/model/horario-funcionamento.model';

@Injectable({
    providedIn: 'root'
})
export class HorarioFuncionamentoService {

    private prefixoApi: string = 'http://localhost:8080/api/horario-funcionamento';

    constructor(private http: HttpClient) { }

    getHorariosFuncionamento(): Observable<HorarioFuncionamento[]> {
        return this.http.get<HorarioFuncionamento[]>(`${this.prefixoApi}`);
    }

    alterarHorariosFuncionamento(horarios: HorarioFuncionamento[]): Observable<void> {
        return this.http.put<void>(`${this.prefixoApi}`, horarios);
    }
}
