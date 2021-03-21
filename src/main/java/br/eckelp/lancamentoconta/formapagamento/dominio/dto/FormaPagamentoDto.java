package br.eckelp.lancamentoconta.formapagamento.dominio.dto;

import br.eckelp.lancamentoconta.formapagamento.dominio.FormaPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamentoDto {

    private Integer id;

    private String descricao;

    public FormaPagamentoDto(FormaPagamento formaPagamento) {
        this.id = formaPagamento.getId();
        this.descricao = formaPagamento.getDescricao();
    }
}
