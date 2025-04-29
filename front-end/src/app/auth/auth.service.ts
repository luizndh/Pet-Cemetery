import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Login } from './model/login.model';
import { Cadastro } from './model/cadastro.model';

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    constructor(private http: HttpClient) { }

    prefixoApi: string = 'http://localhost:8080/api/auth'

    login(login: Login) {
        return this.http.post(`${this.prefixoApi}/login`, login);
    }

    cadastro(cadastro: Cadastro) {
        return this.http.post(`${this.prefixoApi}/cadastro`, cadastro);
    }
}
