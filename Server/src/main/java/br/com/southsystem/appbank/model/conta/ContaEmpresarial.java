package br.com.southsystem.appbank.model.conta;

import br.com.southsystem.appbank.model.TipoConta;
import br.com.southsystem.appbank.model.pessoa.Pessoa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "E")
public class ContaEmpresarial extends Conta {
	@Transient
	private final TipoConta tipo = TipoConta.E;

	public ContaEmpresarial() {
	}

	public ContaEmpresarial(String numero, String agencia, Pessoa pessoa, BigDecimal limiteChequeEspecial) {
		super(numero, agencia, pessoa, limiteChequeEspecial);
	}

	@SuppressWarnings("unused")
	public TipoConta getTipo() {
		return tipo;
	}
}
