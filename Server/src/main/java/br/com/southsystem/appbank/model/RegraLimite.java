package br.com.southsystem.appbank.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
@Entity
@Table(name = "regra_limite")
public class RegraLimite {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "peso")
	private int peso;

	@Column(name = "scoreInicial")
	private int scoreInicial;

	@Column(name = "scoreFinal")
	private int scoreFinal;

	@Column(name = "valorLimite")
	private BigDecimal valorLimite;

	@Column(name = "tipo")
	@Enumerated(EnumType.STRING)
	private TipoRegraLimite tipoLimite;

	@Deprecated
	public RegraLimite() {
	}

	public RegraLimite(TipoRegraLimite tipoLimite, int peso, int scoreInicial, int scoreFinal, BigDecimal valorLimite) {
		this.tipoLimite = tipoLimite;
		this.peso = peso;
		this.scoreInicial = scoreInicial;
		this.scoreFinal = scoreFinal;
		this.valorLimite = valorLimite;
	}


	public static RegraLimiteBuilder builder() {
		return new RegraLimiteBuilder();
	}

	public static class RegraLimiteBuilder {
		private TipoRegraLimite tipoLimite;
		private int peso;
		private int scoreInicial;
		private int scoreFinal;
		private BigDecimal valorLimite;

		private RegraLimiteBuilder() {
		}

		public RegraLimiteBuilder tipoLimite(TipoRegraLimite tipoLimite) {
			this.tipoLimite = Objects.requireNonNull(tipoLimite, "Informe o tipo de limite");
			return this;
		}

		public RegraLimiteBuilder peso(int peso) {
			this.peso = peso;
			return this;
		}

		public RegraLimiteBuilder scoreInicial(int scoreInicial) {
			this.scoreInicial = scoreInicial;
			return this;
		}

		public RegraLimiteBuilder scoreFinal(int scoreFinal) {
			this.scoreFinal = scoreFinal;
			return this;
		}

		public RegraLimiteBuilder valorLimite(BigDecimal valorLimite) {
			this.valorLimite = Objects.requireNonNull(valorLimite, "Informe o valor do limite");
			return this;
		}

		public RegraLimite build() throws Exception {
			Objects.requireNonNull(valorLimite, "Informe o valor do limite");

			if (scoreFinal < scoreInicial) {
				throw new Exception("O score final não pode ser menor que o inicial");
			}

			return new RegraLimite(tipoLimite, peso, scoreInicial, scoreFinal, valorLimite);
		}
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TipoRegraLimite getTipoLimite() {
		return tipoLimite;
	}

	public void setTipoLimite(TipoRegraLimite tipoLimite) {
		this.tipoLimite = tipoLimite;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getScoreInicial() {
		return scoreInicial;
	}

	public void setScoreInicial(int scoreInicial) {
		this.scoreInicial = scoreInicial;
	}

	public int getScoreFinal() {
		return scoreFinal;
	}

	public void setScoreFinal(int scoreFinal) {
		this.scoreFinal = scoreFinal;
	}

	public BigDecimal getValorLimite() {
		return valorLimite;
	}

	public void setValorLimite(BigDecimal valorLimite) {
		this.valorLimite = valorLimite;
	}
}
