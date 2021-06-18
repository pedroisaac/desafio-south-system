package br.com.southsystem.appbank.repository;

import br.com.southsystem.appbank.model.pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	List<Pessoa> findByNomeContaining(String nome);
}
