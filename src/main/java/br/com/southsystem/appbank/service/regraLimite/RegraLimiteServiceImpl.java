package br.com.southsystem.appbank.service.regraLimite;

import br.com.southsystem.appbank.exception.NotFoundException;
import br.com.southsystem.appbank.model.RegraLimite;
import br.com.southsystem.appbank.model.TipoRegraLimite;
import br.com.southsystem.appbank.repository.RegraLimiteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RegraLimiteServiceImpl implements RegraLimiteService {

	private final RegraLimiteRepository repository;

	public RegraLimiteServiceImpl(RegraLimiteRepository repository) {
		this.repository = repository;
	}

	@Override
	public RegraLimite save(RegraLimite regraLimite) throws Exception {
		return repository.save(regraLimite);
	}

	@Override
	public List<RegraLimite> listaTodas() {
		return this.repository.findAll();
	}

	@Override
	public List<RegraLimite> listaByTipoLimiteOrderByPeso(TipoRegraLimite tipoLimite) {
		return this.repository.listaByTipoLimiteOrderByPeso(tipoLimite);
	}


	@Override
	public RegraLimite getById(long id) throws Exception {
		var regraLimiteData = this.repository.findById(id);

		if (regraLimiteData.isPresent()) {
			return regraLimiteData.get();
		} else {
			throw new NotFoundException("Regra de Limite não encontrada com o ID: " + id);
		}
	}

	@Override
	public void delete(long id) throws Exception {
		var regraLimiteData = this.repository.findById(id);

		if (regraLimiteData.isPresent()) {
			this.repository.delete(regraLimiteData.get());
		} else {
			throw new NotFoundException("Regra de Limite não encontrada com o ID: " + id);
		}
	}

	@Override
	public void deleteAll() {
		this.repository.deleteAll();
	}

	@Override
	public BigDecimal getValorLimite(int score, TipoRegraLimite tipoLimite) throws Exception {

		var regras = new ArrayList<>(listaByTipoLimiteOrderByPeso(tipoLimite));

		if (regras.isEmpty()) {
			throw new Exception("Não á regras de limites cadastradas");
		}

		for (RegraLimite regra : regras) {
			if (score >= regra.getScoreInicial() && score <= regra.getScoreFinal()) {
				return regra.getValorLimite();
			}
		}
		return BigDecimal.ZERO;
	}

}
