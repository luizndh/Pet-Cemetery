import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";
import { EscolhaJazigo } from "../../compra/escolha-jazigo.model";

@Injectable({
  providedIn: 'root'
})
export class ContratacaoStateService {
  private escolhaJazigoSource = new BehaviorSubject<EscolhaJazigo | null>(null);

  escolhaJazigo$ = this.escolhaJazigoSource.asObservable();

  constructor() { }

  setEscolhaJazigo(escolha: EscolhaJazigo) {
    this.escolhaJazigoSource.next(escolha);
  }
}