import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Conta} from '../models/conta.model';

const baseUrl = 'http://localhost:8080/api/contas';

@Injectable({
  providedIn: 'root'
})
export class ContaService {

  constructor(private http: HttpClient) {
  }

  listaTodas(): Observable<Conta[]> {
    return this.http.get<Conta[]>(baseUrl);
  }

  get(id: any): Observable<Conta> {
    return this.http.get(`${baseUrl}/${id}`);
  }

  findByIdPessoa(idPessoa: any): Observable<Conta[]> {
    return this.http.get<Conta[]>(`${baseUrl}?idPessoa=${idPessoa}`);
  }

  findByNomePessoa(nomePessoa: any): Observable<Conta[]> {
    return this.http.get<Conta[]>(`${baseUrl}?nomePessoa=${nomePessoa}`);
  }
}
