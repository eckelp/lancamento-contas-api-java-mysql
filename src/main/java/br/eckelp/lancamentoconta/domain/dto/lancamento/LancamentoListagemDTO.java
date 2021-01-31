package br.eckelp.lancamentoconta.domain.dto.lancamento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoListagemDTO {

    private Integer id;

    private LocalDate data;

    private String descricao;

    private BigDecimal valor;

    private String descricaoCategoria;

    private String descricaoFormaPagamento;


}
