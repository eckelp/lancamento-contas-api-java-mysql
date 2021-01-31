package br.eckelp.lancamentoconta.repository;

import br.eckelp.lancamentoconta.domain.dto.formaPagamento.FormaPagamentoListagemDTO;
import br.eckelp.lancamentoconta.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Integer> {

    @Query("SELECT new br.eckelp.lancamentoconta.domain.dto.formaPagamento.FormaPagamentoListagemDTO(" +
            "      fp.id " +
            "     ,fp.descricao " +
            ")" +
            " FROM FormaPagamento fp")
    List<FormaPagamentoListagemDTO> listarFormasPagamento();
}
