package br.com.southsystem.appbank.model.pessoa;

import br.com.southsystem.appbank.model.TipoPessoa;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
@Entity
@DiscriminatorValue(value = "PJ")
public class PessoaJuridica extends Pessoa {

	@Column(name = "cnpj")
	private String cnpj;

	@Transient
	private final TipoPessoa tipo = TipoPessoa.PJ;

	@Deprecated
	public PessoaJuridica() {
	}

	public PessoaJuridica(String nome, String cnpj, int score) {
		super(nome, score);
		this.cnpj = cnpj;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public TipoPessoa getTipo() {
		return tipo;
	}

	@Override
	public String toString() {
		return "Pessoa Jurídica [id=" + getId() + ", nome=" + getNome() + ", cnpj=" + this.cnpj + ", score=" + getScore() + "]";
	}

}
