export class ItemCarrinho {
    idJazigo: number;
    enderecoJazigo: string;
    tipoServico: string;
    valorServico: number;
    planoSelecionado: string | null;
    valorPlano: number;

    constructor(idJazigo: number, enderecoJazigo: string, tipoServico: string, valorServico: number, planoSelecionado: string | null = null, valorPlano: number = 0) {
        this.idJazigo = idJazigo;
        this.enderecoJazigo = enderecoJazigo;
        this.tipoServico = tipoServico;
        this.valorServico = valorServico;
        this.planoSelecionado = planoSelecionado;
        this.valorPlano = valorPlano;
    }
}