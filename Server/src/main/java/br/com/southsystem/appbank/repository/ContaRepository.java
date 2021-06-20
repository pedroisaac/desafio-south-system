package br.com.southsystem.appbank.repository;

import br.com.southsystem.appbank.model.conta.Conta;
import br.com.southsystem.appbank.model.pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContaRepository extends JpaRepository<Conta, Long> {
	List<Conta> findByPessoa(Pessoa pessoa);

	@Query("select case when count(c)> 0 then true else false end from Conta c where c.numero like :numero")
	boolean existeContaComNumero(@Param("numero") String numero);
}
