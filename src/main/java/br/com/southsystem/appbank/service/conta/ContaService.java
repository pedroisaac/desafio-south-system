package br.com.southsystem.appbank.service.conta;

import br.com.southsystem.appbank.model.conta.Conta;
import br.com.southsystem.appbank.model.pessoa.Pessoa;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@SuppressWarnings("unused")
public interface ContaService {
	Conta save(Conta conta) throws Exception;

	Conta update(Conta conta) throws Exception;

	List<Conta> listaTodas() throws Exception;

	List<Conta> listaByPessoa(Pessoa pessoa) throws Exception;

	Conta getById(long id) throws Exception;

	@Query("select case when count(c)> 0 then true else false end from Conta c where c.numero like :numero")
	boolean existeContaComNumero(String numero) throws Exception;

	void delete(long id) throws Exception;

	void deleteAll() throws Exception;
}
