package br.com.southsystem.appbank.service.cartaoCredito;

import br.com.southsystem.appbank.model.conta.CartaoCredito;

import java.util.List;

@SuppressWarnings("unused")
public interface CartaoCreditoService {
	CartaoCredito save(CartaoCredito cartaoCredito) throws Exception;

	List<CartaoCredito> listaTodos() throws Exception;

	CartaoCredito getById(long id) throws Exception;

	void delete(long id) throws Exception;
}
