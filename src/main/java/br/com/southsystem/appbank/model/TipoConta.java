package br.com.southsystem.appbank.model;

public enum TipoConta {
	C("Conta Corrente"),
	E("Conta Empresarial");

	private final String descricao;

	TipoConta(String descricao) {
		this.descricao = descricao;
	}

	@SuppressWarnings("unused")
	public String getDescricao() {
		return descricao;
	}
}
