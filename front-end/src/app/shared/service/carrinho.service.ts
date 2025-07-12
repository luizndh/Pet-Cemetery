import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ItemCarrinho } from '../model/item-carrinho.model';

@Injectable({
  providedIn: 'root'
})
export class CarrinhoService {

    private prefixoApi: string = 'http://localhost:8080/api/carrinho';

    constructor(private http: HttpClient) { }

    buscarCarrinho(): Observable<ItemCarrinho[]> {
        return this.http.get<ItemCarrinho[]>(`${this.prefixoApi}`);
    }

    adicionarItemAoCarrinho(item: ItemCarrinho): Observable<ItemCarrinho> {
        return this.http.post<ItemCarrinho>(`${this.prefixoApi}`, item);
    }
}