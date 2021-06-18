package br.com.southsystem.appbank.model.pessoa;

import br.com.southsystem.appbank.model.TipoPessoa;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
@Entity
@DiscriminatorValue(value = "PF")
public class PessoaFisica extends Pessoa {

	@Column(name = "cpf")
	private String cpf;

	@Transient
	private final TipoPessoa tipo = TipoPessoa.PF;

	@Deprecated
	public PessoaFisica() {}

	public PessoaFisica(String nome, String cpf, int score) {
		super(nome, score);
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public TipoPessoa getTipo() {
		return tipo;
	}

	@Override
	public String toString() {
		return "Pessoa Física [id=" + getId() + ", nome=" + getNome() + ", cpf=" + this.cpf + ", score=" + getScore() + "]";
	}
}
