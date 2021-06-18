package br.com.southsystem.appbank.model.conta;

import br.com.southsystem.appbank.model.TipoConta;
import br.com.southsystem.appbank.model.pessoa.Pessoa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "C")
public class ContaCorrente extends Conta {

	@Transient
	private final TipoConta tipo = TipoConta.C;


	public ContaCorrente() {
	}

	public ContaCorrente(String numero, String agencia, Pessoa pessoa, BigDecimal limiteChequeEspecial) {
		super(numero, agencia, pessoa, limiteChequeEspecial);
	}

	@SuppressWarnings("unused")
	public TipoConta getTipo() {
		return tipo;
	}
}
