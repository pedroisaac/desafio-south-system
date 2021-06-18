package br.com.southsystem.appbank.model;

public enum TipoPessoa {
	PF("Pessoa Fisíca"),
	PJ("Pessoa Jurídica");

	private final String descricao;

	TipoPessoa(String descricao) {
		this.descricao = descricao;
	}

	@SuppressWarnings("unused")
	public String getDescricao() {
		return descricao;
	}
}
