package br.eckelp.lancamentoconta.domain.repository;

import br.eckelp.lancamentoconta.resource.dto.lancamento.LancamentoDto;
import br.eckelp.lancamentoconta.domain.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Integer> {

    @Query("    SELECT l " +
            "     FROM Lancamento l " +
            "    WHERE l.data between :dataInicial AND :dataFinal " +
            " ORDER BY l.data desc"
    )
    List<Lancamento> listarLancamentos(LocalDate dataInicial, LocalDate dataFinal);

    @Override
    @Modifying
    @Query(value = "DELETE FROM Lancamento l WHERE l.id = :lancamentoId")
    void deleteById(Integer lancamentoId);
}
