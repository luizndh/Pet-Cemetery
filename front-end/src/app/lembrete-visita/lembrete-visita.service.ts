import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LembreteVisitaService {

    constructor(private http: HttpClient) {}

    recuperaDadosMeteorologicos(): Observable<any> {
        const apiKey = 'SUA_API_KEY'; // Substitua pela sua chave de API do OpenWeatherMap
        const city = 'Rio de Janeiro'; // Substitua pela cidade desejada
        return this.http.get(`https://api.openweathermap.org/data/2.5/forecast?q=${city}&appid=${apiKey}&units=metric&cnt=40`);
    }
}
