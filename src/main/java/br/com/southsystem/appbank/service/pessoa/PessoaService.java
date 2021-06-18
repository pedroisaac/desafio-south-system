package br.com.southsystem.appbank.service.pessoa;

import br.com.southsystem.appbank.model.pessoa.Pessoa;

import java.util.List;

public interface PessoaService {
	Pessoa save(Pessoa pessoa) throws Exception;

	@SuppressWarnings("unused")
	Pessoa update(Pessoa pessoa) throws Exception;

	List<Pessoa> listaTodos() throws Exception;

	List<Pessoa> listaByNome(String nome) throws Exception;

	Pessoa getById(long id) throws Exception;

	void delete(long id) throws Exception;

	void deleteAll() throws Exception;
}
