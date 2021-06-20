package br.com.southsystem.appbank.service.pessoa;

import br.com.southsystem.appbank.exception.NotFoundException;
import br.com.southsystem.appbank.model.pessoa.Pessoa;
import br.com.southsystem.appbank.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.Queue;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Service
@Transactional
public class PessoaServiceImpl implements PessoaService {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private Queue queue;

	private final PessoaRepository repository;

	public PessoaServiceImpl(PessoaRepository repository) {
		this.repository = repository;
	}

	@Override
	public Pessoa save(Pessoa pessoa) throws Exception {
		var _pessoa = repository.save(pessoa);
		solicitarCriacaoContaJMS(_pessoa);
		return _pessoa;
	}

	@Override
	public Pessoa update(Pessoa pessoa) {
		var pessoaData = this.repository.findById(pessoa.getId());

		if (pessoaData.isPresent()) {
			var _pessoa = pessoaData.get();
			_pessoa.setNome(pessoa.getNome());

			repository.save(_pessoa);
			return _pessoa;
		} else {
			throw new NotFoundException("Pessoa não encontrada com o ID: " + pessoa.getId());
		}
	}

	// envia a pessoa pra fila de geração de conta e limites
	public void solicitarCriacaoContaJMS(Pessoa pessoa) {
		this.jmsTemplate.convertAndSend(queue, pessoa);
	}

	@Override
	public List<Pessoa> listaTodos() {
		return this.repository.findAll();
	}

	@Override
	public List<Pessoa> listaByNome(String nome) {
		return this.repository.findByNomeContaining(nome);
	}

	@Override
	public Pessoa getById(long id) throws Exception {
		var pessoaData = this.repository.findById(id);

		if (pessoaData.isPresent()) {
			return pessoaData.get();
		} else {
			throw new NotFoundException("Pessoa não encontrada com o ID: " + id);
		}
	}

	@Override
	public void delete(long id) throws Exception {
		var pessoaData = this.repository.findById(id);

		if (pessoaData.isPresent()) {
			this.repository.delete(pessoaData.get());
		} else {
			throw new NotFoundException("Pessoa não encontrada com o ID: " + id);
		}
	}

	@Override
	public void deleteAll() {
		this.repository.deleteAll();
	}

}
