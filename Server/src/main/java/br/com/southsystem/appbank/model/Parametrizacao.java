package br.com.southsystem.appbank.model;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Parametrizacao implements Serializable {
	public static final String FILE_PARAMETRIZACAO = "parametrizacao.xml";

	private String agenciaPadrao;

	public Parametrizacao() {
	}

	public Parametrizacao(String agenciaConta) {
		this.agenciaPadrao = agenciaConta;
	}

	public String getAgenciaPadrao() {
		return agenciaPadrao;
	}

	public void setAgenciaPadrao(String agenciaPadrao) {
		this.agenciaPadrao = agenciaPadrao;
	}
}
