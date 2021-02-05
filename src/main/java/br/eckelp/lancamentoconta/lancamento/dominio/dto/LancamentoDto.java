package br.eckelp.lancamentoconta.lancamento.dominio.dto;

import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoDto {

    private Integer id;

    private LocalDate data;

    private String descricao;

    private BigDecimal valor;

    private String descricaoCategoria;

    private String descricaoFormaPagamento;

    public LancamentoDto(Lancamento lancamento) {
        this.id = lancamento.getId();
        this.data = lancamento.getData();
        this.descricao = lancamento.getDescricao();
        this.valor = lancamento.getValor();
        this.descricaoCategoria = lancamento.getDescricaoCategoria();
        this.descricaoFormaPagamento = lancamento.getDescricaoFormaPagamento();
    }
}
