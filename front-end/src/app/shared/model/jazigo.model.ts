import { Pet } from "./pet.model";

export interface Jazigo {
    endereco: string;
    idJazigo: number;
    mensagem: string | null;
    foto: any;
    petEnterrado: Pet;
    status: string;
}