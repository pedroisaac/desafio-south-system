package br.com.southsystem.appbank.model.conta;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Year;
import java.util.Objects;
import java.util.Random;

@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
@Entity
@Table(name = "cartao_credito", uniqueConstraints = {@UniqueConstraint(columnNames = {"numero"})})
public class CartaoCredito implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "numero")
	private String numero;

	@Column(name = "cvv")
	private String cvv;

	@Column(name = "anoVencimento")
	private Year anoVencimento;

	@Column(name = "mesVencimento")
	private int mesVencimento;

	@Column(name = "valorLimite")
	private BigDecimal valorLimite;

	@Column(name = "diaVencimentoFatura")
	private int diaVencimentoFatura;

	@Deprecated
	public CartaoCredito() {
	}

	public CartaoCredito(String numero, String cvv, Year anoVencimento, int mesVencimento, BigDecimal valorLimite, int diaVencimentoFatura) {
		this.numero = numero;
		this.cvv = cvv;
		this.anoVencimento = anoVencimento;
		this.mesVencimento = mesVencimento;
		this.valorLimite = valorLimite;
		this.diaVencimentoFatura = diaVencimentoFatura;
	}

	public static CartaoCreditoBuilder builder() {
		return new CartaoCreditoBuilder();
	}

	public static class CartaoCreditoBuilder {
		private BigDecimal valorLimite;

		private CartaoCreditoBuilder() {
		}

		public CartaoCreditoBuilder valorLimite(BigDecimal valorLimite) {
			this.valorLimite = Objects.requireNonNull(valorLimite, "Informe o Limite do Cartão");
			return this;
		}

		public CartaoCredito build() throws Exception {
			Objects.requireNonNull(valorLimite, "Informe o Limite do Cartão");

			var random = new Random();
			var cvv = String.format("%03d", random.nextInt(999));
			var anoVenc = Year.now();
			var mesVenc = random.nextInt(11) + 1;
			var diaFechamento = random.nextInt(30) + 1;

			String numeroCartao = String.format("%04d", random.nextInt(9999)) + "-" +
					String.format("%04d", random.nextInt(9999)) + "-" +
					String.format("%04d", random.nextInt(9999)) + "-" +
					String.format("%04d", random.nextInt(9999));

			return new CartaoCredito(numeroCartao, cvv, anoVenc, mesVenc, valorLimite, diaFechamento);
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

	public String getCvv() {
		return cvv;
	}

	public Year getAnoVencimento() {
		return anoVencimento;
	}

	public int getMesVencimento() {
		return mesVencimento;
	}

	public BigDecimal getValorLimite() {
		return valorLimite;
	}

	public int getDiaVencimentoFatura() {
		return diaVencimentoFatura;
	}

	public void setDiaVencimentoFatura(int diaVencimentoFatura) {
		this.diaVencimentoFatura = diaVencimentoFatura;
	}
}
