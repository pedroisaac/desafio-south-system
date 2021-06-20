package br.com.southsystem.appbank.model.pessoa;

import br.com.southsystem.appbank.utils.Utils;

import javax.persistence.*;
import java.util.Objects;
import java.util.Random;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
@Table(name = "pessoa", uniqueConstraints = {@UniqueConstraint(columnNames = {"cpf"}), @UniqueConstraint(columnNames = {"cnpj"})})
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "score")
	private int score;

	@Deprecated
	public Pessoa() {
	}

	public Pessoa(String nome, int score) {
		this.nome = nome;
		this.score = score;
	}

	public static PessoaBuilder builder() {
		return new PessoaBuilder();
	}

	public static class PessoaBuilder {
		private String nome;
		private String documento;

		private PessoaBuilder() {
		}

		public PessoaBuilder nome(String nome) {
			this.nome = Objects.requireNonNull(nome, "O nome é obrigatório");
			return this;
		}

		public PessoaBuilder documento(String documento) {
			this.documento = Objects.requireNonNull(documento, "Informe um documento CPF ou CNPJ");
			return this;
		}

		public Pessoa build() throws Exception {
			Objects.requireNonNull(nome, "O nome é obrigatório");
			Objects.requireNonNull(documento, "Informe um documento CPF ou CNPJ");

			var random = new Random();
			var score = random.nextInt(10);

			if (Utils.isCPF(documento)) {
				return new PessoaFisica(nome, documento, score);
			} else if (Utils.isCNPJ(documento)) {
				return new PessoaJuridica(nome, documento, score);
			} else {
				throw new Exception("O documento informado é inválido");
			}
		}
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getScore() {
		return score;
	}

	@SuppressWarnings("unused")
	public void setScore(int score) {
		this.score = score;
	}
}
