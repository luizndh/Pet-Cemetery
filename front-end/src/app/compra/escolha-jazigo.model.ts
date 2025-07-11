import { Servico } from "../shared/model/servico.model";

export interface EscolhaJazigo {
    enderecoJazigo: string;
    idJazigo: number;
    compraOuAluguel: Servico;
    planoEscolhido: Servico | null;
}