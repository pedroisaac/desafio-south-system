import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PessoaAddComponent } from './components/pessoa-add/pessoa-add.component';
import { PessoaDetalhesComponent } from './components/pessoa-detalhes/pessoa-detalhes.component';
import { PessoasListaComponent } from './components/pessoas-lista/pessoas-lista.component';
import { ContasListaComponent } from './components/contas-lista/contas-lista.component';

@NgModule({
  declarations: [
    AppComponent,
    PessoaAddComponent,
    PessoaDetalhesComponent,
    PessoasListaComponent,
    ContasListaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
