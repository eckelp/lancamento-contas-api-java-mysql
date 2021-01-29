package br.eckelp.lancamentoconta.domain.dto.lancamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ILancamentoDTO {

    String getDescricao();
    Integer getCategoriaId();
    Integer getFormaPagamentoId();
    BigDecimal getValor();
    LocalDate getData();

}
