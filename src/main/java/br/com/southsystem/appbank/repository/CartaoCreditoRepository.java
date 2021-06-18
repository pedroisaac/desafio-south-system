package br.com.southsystem.appbank.repository;

import br.com.southsystem.appbank.model.conta.CartaoCredito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Long> {
}
