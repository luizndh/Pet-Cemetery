import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HorarioFuncionamento } from './model/horario-funcionamento.model';

@Injectable({
    providedIn: 'root'
})
export class HorarioFuncionamentoService {

    private prefixoApi: string = 'http://localhost:8080/api/horario-funcionamento';

    constructor(private http: HttpClient) { }

    getHorariosFuncionamento(): Observable<HorarioFuncionamento[]> {
        return this.http.get<HorarioFuncionamento[]>(`${this.prefixoApi}`);
    }
}
