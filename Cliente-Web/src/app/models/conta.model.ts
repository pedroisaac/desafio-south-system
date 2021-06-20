import {Pessoa} from './pessoa.model';
import {CartaoCredito} from './cartaoCredito.model';

export class Conta {
  id?: any;
  numero?: string;
  agencia?: string;
  pessoa?: Pessoa;
  cartaoCredito?: CartaoCredito;
  limiteChequeEspecial?: number;
  tipo?: string;
}
