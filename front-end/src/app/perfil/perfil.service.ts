import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DadosPerfilInicial } from './model/dados-perfil-inicial.model';
import { Observable } from 'rxjs';
import { DadosPerfil } from './model/dados-perfil.model';

@Injectable({
    providedIn: 'root'
})
export class PerfilService {

    prefixoApi: string = 'http://localhost:8080/api/cliente'

    constructor(private http: HttpClient) { }

    trocarSenha(value: { senhaAtual: string, novaSenha: string, confirmacaoSenha: string }): Observable<void> {
        return this.http.put<void>(`${this.prefixoApi}/trocar-senha`, value);
    }

    editarPerfil(formValue: DadosPerfil): Observable<DadosPerfil> {
        return this.http.put<DadosPerfil>(`${this.prefixoApi}/alterar`, formValue);
    }

    getPerfilInicial(): Observable<DadosPerfilInicial> {
        return this.http.get<DadosPerfilInicial>(`${this.prefixoApi}`);
    }

    getPerfil(): Observable<DadosPerfil> {
        return this.http.get<DadosPerfil>(`${this.prefixoApi}/alterar`);
    }

    desativarPerfil() {
        return this.http.post<void>(`${this.prefixoApi}/desativar`, null);
    }
}
