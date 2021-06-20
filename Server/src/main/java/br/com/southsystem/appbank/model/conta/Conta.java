package br.com.southsystem.appbank.model.conta;

import br.com.southsystem.appbank.model.pessoa.Pessoa;
import br.com.southsystem.appbank.model.pessoa.PessoaFisica;
import br.com.southsystem.appbank.model.pessoa.PessoaJuridica;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Random;

@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
@Table(name = "conta", uniqueConstraints = {@UniqueConstraint(columnNames = {"numero"})})
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "numero")
	private String numero;

	@Column(name = "agencia")
	private String agencia;

	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_cartao_credito")
	private CartaoCredito cartaoCredito;

	@Column(name = "limite_cheque_especial")
	private BigDecimal limiteChequeEspecial;


	public Conta() {
	}

	public Conta(String numero, String agencia, Pessoa pessoa, BigDecimal limiteChequeEspecial) {
		this.numero = numero;
		this.agencia = agencia;
		this.pessoa = pessoa;
		this.limiteChequeEspecial = limiteChequeEspecial;
	}


	public static ContaBuilder builder() {
		return new ContaBuilder();
	}

	public static class ContaBuilder {

		private String agencia;
		private Pessoa pessoa;
		private BigDecimal limiteCheque;

		private ContaBuilder() {
		}

		public ContaBuilder agencia(String agencia) {
			this.agencia = Objects.requireNonNull(agencia, "Informe o número da Agência");
			return this;
		}

		public ContaBuilder pessoa(Pessoa pessoa) {
			this.pessoa = Objects.requireNonNull(pessoa, "Informe o cliente da Conta");
			return this;
		}

		public ContaBuilder limiteChequeEspecial(BigDecimal limiteCheque) {
			this.limiteCheque = limiteCheque;
			return this;
		}

		public Conta build() throws Exception {
			Objects.requireNonNull(agencia, "Informe o número da Agência");
			Objects.requireNonNull(pessoa, "Informe o cliente da Conta");

			var random = new Random();
			var numeroConta = String.format("%06d", random.nextInt(999999));

			if (pessoa instanceof PessoaFisica) {
				return new ContaCorrente(numeroConta, agencia, pessoa, limiteCheque);
			} else if (pessoa instanceof PessoaJuridica) {
				return new ContaEmpresarial(numeroConta, agencia, pessoa, limiteCheque);
			} else {
				throw new Exception("O tipo de pessoa é inválido");
			}
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public CartaoCredito getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(CartaoCredito cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}

	public BigDecimal getLimiteChequeEspecial() {
		return limiteChequeEspecial;
	}
}
