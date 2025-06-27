import { Pet } from "./pet.model";

export interface Jazigo {
    id: number;
    endereco: string;
    idJazigo: number;
    mensagem: string | null;
    foto: any;
    petEnterrado: Pet;
    status: string;
    plano: string;
}