import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PessoasListaComponent } from './components/pessoas-lista/pessoas-lista.component';
import { ContasListaComponent } from './components/contas-lista/contas-lista.component';
import { PessoaDetalhesComponent } from './components/pessoa-detalhes/pessoa-detalhes.component';
import { PessoaAddComponent } from './components/pessoa-add/pessoa-add.component';

const routes: Routes = [
  { path: '', redirectTo: 'pessoas', pathMatch: 'full' },
  { path: 'pessoas', component: PessoasListaComponent },
  { path: 'contas', component: ContasListaComponent },
  { path: 'pessoas/:id', component: PessoaDetalhesComponent },
  { path: 'add', component: PessoaAddComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
