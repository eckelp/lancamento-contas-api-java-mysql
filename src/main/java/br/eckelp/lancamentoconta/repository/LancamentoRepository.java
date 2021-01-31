package br.eckelp.lancamentoconta.repository;

import br.eckelp.lancamentoconta.domain.dto.lancamento.LancamentoListagemDTO;
import br.eckelp.lancamentoconta.domain.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Integer> {

    @Query("    SELECT new br.eckelp.lancamentoconta.domain.dto.lancamento.LancamentoListagemDTO(" +
            "          l.id " +
            "         ,l.data " +
            "         ,l.descricao " +
            "         ,l.valor " +
            "         ,l.categoria.descricao " +
            "         ,l.formaPagamento.descricao " +
            " ) " +
            "     FROM Lancamento l " +
            "    WHERE l.data between :dataInicial AND :dataFinal " +
            " ORDER BY l.data desc"
    )
    List<LancamentoListagemDTO> listarLancamentos(LocalDate dataInicial, LocalDate dataFinal);
}
