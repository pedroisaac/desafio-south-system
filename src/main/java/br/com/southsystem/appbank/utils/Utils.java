package br.com.southsystem.appbank.utils;

import org.springframework.util.ObjectUtils;

public class Utils {

	public static String extrairNumeros(String numero) {
		if (numero == null)return null;
		return numero.replaceAll("[^0-9]*", "");
	}

	public static boolean isCPF(String cpf) {
		var aux = extrairNumeros(cpf);
		return !ObjectUtils.isEmpty(aux) && aux.length() == 11;
	}

	public static boolean isCNPJ(String cnpj) {
		var aux = extrairNumeros(cnpj);
		return !ObjectUtils.isEmpty(aux) && aux.length() == 14;
	}

	public static String formataCPF(String cpf) {
		if (cpf != null && cpf.length() == 11) {
			cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
		}
		return cpf;
	}

	public static String formataCNJ(String cnj) {
		if (cnj != null && cnj.length() == 20) {
			cnj = cnj.substring(0, 7) + "-" + cnj.substring(7, 9) + "."
					+ cnj.substring(9, 13) + "." + cnj.substring(13, 16) + "."
					+ cnj.substring(16, 20);
		}
		return cnj;
	}

	@SuppressWarnings("unused")
	public static String formataCpfCnpj(String cpfCnpj) {
		if (extrairNumeros(cpfCnpj).length() > 11) {
			return formataCNJ(cpfCnpj);
		}
		return formataCPF(cpfCnpj);
	}

}
