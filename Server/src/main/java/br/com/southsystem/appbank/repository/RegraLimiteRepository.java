package br.com.southsystem.appbank.repository;

import br.com.southsystem.appbank.model.RegraLimite;
import br.com.southsystem.appbank.model.TipoRegraLimite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegraLimiteRepository extends JpaRepository<RegraLimite, Long> {
	@Query("select r from RegraLimite r where r.tipoLimite = :#{#tipoLimite}")
	List<RegraLimite> listaByTipoLimiteOrderByPeso(@Param("tipoLimite") TipoRegraLimite tipoLimite);
}
