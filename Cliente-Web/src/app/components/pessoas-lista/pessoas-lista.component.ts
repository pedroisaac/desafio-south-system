import {Component, OnInit} from '@angular/core';
import {Pessoa} from 'src/app/models/pessoa.model';
import {PessoaService} from 'src/app/services/pessoa.service';
import {Conta} from '../../models/conta.model';
import {ContaService} from '../../services/conta.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-pessoas-lista',
  templateUrl: './pessoas-lista.component.html',
  styleUrls: ['./pessoas-lista.component.css']
})
export class PessoasListaComponent implements OnInit {
  pessoas?: Pessoa[];
  contas?: Conta[];
  currentPessoa?: Pessoa;
  currentConta?: Conta;
  currentIndex = -1;
  nome = '';

  constructor(private pessoaService: PessoaService, private contaService: ContaService) {
  }

  ngOnInit(): void {
    this.listaPessoas();
  }

  listaPessoas(): void {
    this.pessoaService.listaTodas()
      .subscribe(
        data => {
          this.pessoas = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  refreshLista(): void {
    this.listaPessoas();
    this.currentPessoa = undefined;
    this.currentConta = undefined;
    this.currentIndex = -1;
  }

  setPessoaSelecionada(pessoa: Pessoa, index: number): void {
    this.currentPessoa = pessoa;
    this.currentIndex = index;
    this.contaService.findByIdPessoa(pessoa.id)
      .subscribe(
        data => {
          this.contas = data;
          if (this.contas != null && this.contas.length > 0) {
            this.currentConta = this.contas[0];
          }
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  removeTodasPessoas(): void {
    this.pessoaService.deleteAll()
      .subscribe(
        response => {
          console.log(response);
          this.refreshLista();
        },
        error => {
          console.log(error);
        });
  }

  buscaByNome(): void {
    this.currentPessoa = undefined;
    this.currentIndex = -1;

    this.pessoaService.findByNome(this.nome)
      .subscribe(
        data => {
          this.pessoas = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

}
