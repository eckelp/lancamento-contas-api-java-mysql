package br.eckelp.lancamentoconta.repository;

import br.eckelp.lancamentoconta.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Integer> {
}
