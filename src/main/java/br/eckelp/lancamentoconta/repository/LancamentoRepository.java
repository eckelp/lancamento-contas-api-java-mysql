package br.eckelp.lancamentoconta.repository;

import br.eckelp.lancamentoconta.domain.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Integer> {
}
