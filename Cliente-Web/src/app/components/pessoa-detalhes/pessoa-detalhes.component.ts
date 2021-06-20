import { Component, OnInit } from '@angular/core';
import { PessoaService } from 'src/app/services/pessoa.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Pessoa } from 'src/app/models/pessoa.model';

@Component({
  selector: 'app-pessoa-detalhes',
  templateUrl: './pessoa-detalhes.component.html',
  styleUrls: ['./pessoa-detalhes.component.css']
})
export class PessoaDetalhesComponent implements OnInit {
  currentPessoa: Pessoa = {
    nome: '',
    cpf: '',
    cnpj: '',
    score: 0,
    tipo: ''
  };
  message = '';

  constructor(
    private pessoaService: PessoaService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.message = '';
    this.getPessoa(this.route.snapshot.params.id);
  }

  getPessoa(id: string): void {
    this.pessoaService.get(id)
      .subscribe(
        data => {
          this.currentPessoa = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  updatePessoa(): void {
    this.message = '';

    this.pessoaService.update(this.currentPessoa.id, this.currentPessoa)
      .subscribe(
        response => {
          console.log(response);
          this.message = response.message ? response.message : 'Pessoa atualizada com sucesso!';
        },
        error => {
          console.log(error);
        });
  }

  deletePessoa(): void {
    this.pessoaService.delete(this.currentPessoa.id)
      .subscribe(
        response => {
          console.log(response);
          this.router.navigate(['/pessoas']);
        },
        error => {
          console.log(error);
        });
  }
}
