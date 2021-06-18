package br.com.southsystem.appbank.service.cartaoCredito;

import br.com.southsystem.appbank.exception.NotFoundException;
import br.com.southsystem.appbank.model.conta.CartaoCredito;
import br.com.southsystem.appbank.repository.CartaoCreditoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CartaoCreditoServiceImpl implements CartaoCreditoService {

	private final CartaoCreditoRepository repository;

	public CartaoCreditoServiceImpl(CartaoCreditoRepository repository) {
		this.repository = repository;
	}

	@Override
	public CartaoCredito save(CartaoCredito cartaoCredito) throws Exception {
		return repository.save(cartaoCredito);
	}

	@Override
	public List<CartaoCredito> listaTodos() {
		return this.repository.findAll();
	}

	@Override
	public CartaoCredito getById(long id) throws Exception {
		var cartaoCreditoData = this.repository.findById(id);

		if (cartaoCreditoData.isPresent()) {
			return cartaoCreditoData.get();
		} else {
			throw new NotFoundException("Cartão de Crédito não encontrado com o ID: " + id);
		}
	}

	@Override
	public void delete(long id) throws Exception {
		var cartaoCreditoData = this.repository.findById(id);

		if (cartaoCreditoData.isPresent()) {
			this.repository.delete(cartaoCreditoData.get());
		} else {
			throw new NotFoundException("Cartão Crédito não encontrado com o ID: " + id);
		}
	}
}
