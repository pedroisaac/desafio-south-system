import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pessoa } from '../models/pessoa.model';
const baseUrl = 'http://localhost:8080/api/pessoas';

@Injectable({
  providedIn: 'root'
})
export class PessoaService {

  constructor(private http: HttpClient) { }

  listaTodas(): Observable<Pessoa[]> {
    return this.http.get<Pessoa[]>(baseUrl);
  }

  get(id: any): Observable<Pessoa> {
    return this.http.get(`${baseUrl}/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }

  findByNome(nome: any): Observable<Pessoa[]> {
    return this.http.get<Pessoa[]>(`${baseUrl}?nome=${nome}`);
  }
}
