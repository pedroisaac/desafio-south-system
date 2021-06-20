package br.com.southsystem.appbank.model;

public enum TipoRegraLimite {
	CARTAO_CREDITO("Cartão de Crédito"),
	CHEQUE_ESPECIAL("Cheque Especial");

	private final String descricao;

	TipoRegraLimite(String descricao) {
		this.descricao = descricao;
	}

	@SuppressWarnings("unused")
	public String getDescricao() {
		return descricao;
	}
}
