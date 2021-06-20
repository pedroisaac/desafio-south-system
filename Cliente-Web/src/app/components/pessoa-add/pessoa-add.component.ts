import { Component, OnInit } from '@angular/core';
import { Pessoa } from 'src/app/models/pessoa.model';
import { PessoaService } from 'src/app/services/pessoa.service';

@Component({
  selector: 'app-pessoa-add',
  templateUrl: './pessoa-add.component.html',
  styleUrls: ['./pessoa-add.component.css']
})
export class PessoaAddComponent implements OnInit {
  pessoa: Pessoa = {
    nome: '',
    cpf: '',
    cnpj: '',
    score: 0,
    tipo: ''
  };
  submitted = false;

  constructor(private pessoaService: PessoaService) { }

  ngOnInit(): void {
  }

  savePessoa(): void {
    const data = {
      nome: this.pessoa.nome,
      documento: this.pessoa.cpf
    };

    this.pessoaService.create(data)
      .subscribe(
        response => {
          console.log(response);
          this.submitted = true;
        },
        error => {
          console.log(error);
        });
  }

  newPessoa(): void {
    this.submitted = false;
    this.pessoa = {
      nome: '',
      cpf: '',
      cnpj: '',
      score: 0,
      tipo: ''
    };
  }

}
