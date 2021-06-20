package br.com.southsystem.appbank.service.regraLimite;

import br.com.southsystem.appbank.model.RegraLimite;
import br.com.southsystem.appbank.model.TipoRegraLimite;

import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings({"unused"})
public interface RegraLimiteService {
	RegraLimite save(RegraLimite regraLimite) throws Exception;

	List<RegraLimite> listaTodas() throws Exception;

	List<RegraLimite> listaByTipoLimiteOrderByPeso(TipoRegraLimite tipoLimite) throws Exception;

	RegraLimite getById(long id) throws Exception;

	void delete(long id) throws Exception;

	void deleteAll() throws Exception;

	BigDecimal getValorLimite(int score, TipoRegraLimite tipoLimite) throws Exception;
}
