package br.com.southsystem.appbank.mensageria;

import br.com.southsystem.appbank.AppConfig;
import br.com.southsystem.appbank.model.TipoRegraLimite;
import br.com.southsystem.appbank.model.conta.Conta;
import br.com.southsystem.appbank.model.pessoa.Pessoa;
import br.com.southsystem.appbank.service.conta.ContaService;
import br.com.southsystem.appbank.service.regraLimite.RegraLimiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Component
public class CriadorConta {

	@Autowired
	RegraLimiteService regraLimiteService;

	@Autowired
	ContaService contaService;

	@Autowired
	AppConfig appConfig;

	@JmsListener(destination = "gerar-nova-conta", containerFactory = "myFactory")
	public void receiveMessage(Pessoa pessoa) throws Exception {
		System.out.println("Recebido pedido de abertura de conta: [" + pessoa.toString() + "]");

		var limiteCheque = regraLimiteService.getValorLimite(pessoa.getScore(), TipoRegraLimite.CHEQUE_ESPECIAL);

		var _conta = Conta.builder()
				.agencia(appConfig.getParametrizacao().getAgenciaPadrao())
				.pessoa(pessoa)
				.limiteChequeEspecial(limiteCheque)
				.build();

		contaService.save(_conta);
	}
}
