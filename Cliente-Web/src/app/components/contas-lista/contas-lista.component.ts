import {Component, OnInit} from '@angular/core';
import {ContaService} from '../../services/conta.service';
import {Conta} from '../../models/conta.model';

@Component({
  selector: 'app-contas-lista',
  templateUrl: './contas-lista.component.html',
  styleUrls: ['./contas-lista.component.css']
})
export class ContasListaComponent implements OnInit {
  contas?: Conta[];
  currentConta?: Conta;
  currentIndex = -1;
  nomePessoa = '';

  constructor(private contaService: ContaService) {
  }

  ngOnInit(): void {
    this.listaContas();
  }

  listaContas(): void {
    this.contaService.listaTodas()
      .subscribe(
        data => {
          this.contas = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  refreshLista(): void {
    this.listaContas();
    this.currentConta = undefined;
    this.currentIndex = -1;
  }

  setContaSelecionada(conta: Conta, index: number): void {
    this.currentConta = conta;
    this.currentIndex = index;
  }

  buscaByNomePessoa(): void {
    this.currentConta = undefined;
    this.currentIndex = -1;

    this.contaService.findByNomePessoa(this.nomePessoa)
      .subscribe(
        data => {
          this.contas = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }
}
