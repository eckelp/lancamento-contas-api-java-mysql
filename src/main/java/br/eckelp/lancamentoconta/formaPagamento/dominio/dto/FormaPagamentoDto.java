package br.eckelp.lancamentoconta.formaPagamento.dominio.dto;

import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
import lombok.Data;

@Data
public class FormaPagamentoDto {

    private Integer id;

    private String descricao;

    public FormaPagamentoDto(FormaPagamento formaPagamento) {
        this.id = formaPagamento.getId();
        this.descricao = formaPagamento.getDescricao();
    }
}
