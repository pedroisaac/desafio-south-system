package br.com.southsystem.appbank.service.conta;

import br.com.southsystem.appbank.exception.NotFoundException;
import br.com.southsystem.appbank.model.TipoRegraLimite;
import br.com.southsystem.appbank.model.conta.CartaoCredito;
import br.com.southsystem.appbank.model.conta.Conta;
import br.com.southsystem.appbank.model.pessoa.Pessoa;
import br.com.southsystem.appbank.repository.ContaRepository;
import br.com.southsystem.appbank.service.regraLimite.RegraLimiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Service
public class ContaServiceImpl implements ContaService {

	@Autowired
	private RegraLimiteService regraLimiteService;

	private final ContaRepository repository;

	public ContaServiceImpl(ContaRepository repository) {
		this.repository = repository;
	}

	private void validaNumeroConta(Conta conta) {
		if (existeContaComNumero(conta.getNumero())) {
			var random = new Random();
			var numeroConta = String.format("%06d", random.nextInt(999999));
			conta.setNumero(numeroConta);
			validaNumeroConta(conta);
		}
	}

	@Override
	@Transactional
	public Conta save(Conta conta) throws Exception {
		validaNumeroConta(conta);

		BigDecimal valorLimite = regraLimiteService.getValorLimite(conta.getPessoa().getScore(), TipoRegraLimite.CARTAO_CREDITO);

		if (!Objects.isNull(valorLimite) && valorLimite.compareTo(BigDecimal.ZERO) > 0) {
			var cartaoCredito =
					CartaoCredito.builder()
							.valorLimite(valorLimite)
							.build();
			conta.setCartaoCredito(cartaoCredito);
		}

		return repository.save(conta);
	}

	@Override
	public Conta update(Conta conta) {
		var contaData = this.repository.findById(conta.getId());

		if (contaData.isPresent()) {
			var _conta = contaData.get();
			_conta.setAgencia(conta.getAgencia());
			_conta.setPessoa(conta.getPessoa());

			repository.save(_conta);
			return _conta;
		} else {
			throw new NotFoundException("Conta não encontrada com o ID: " + conta.getId());
		}
	}

	@Override
	public List<Conta> listaTodas() {
		return this.repository.findAll();
	}

	@Override
	public List<Conta> listaByPessoa(Pessoa pessoa) {
		return this.repository.findByPessoa(pessoa);
	}

	@Override
	public Conta getById(long id) throws Exception {
		var contaData = this.repository.findById(id);

		if (contaData.isPresent()) {
			return contaData.get();
		} else {
			throw new NotFoundException("Conta não encontrada com o ID: " + id);
		}
	}

	@Override
	public boolean existeContaComNumero(String numero) {
		return repository.existeContaComNumero(numero);
	}

	@Override
	public void delete(long id) throws Exception {
		var contaData = this.repository.findById(id);

		if (contaData.isPresent()) {
			this.repository.delete(contaData.get());
		} else {
			throw new NotFoundException("Conta não encontrada com o ID: " + id);
		}
	}

	@Override
	public void deleteAll() {
		this.repository.deleteAll();
	}

}
