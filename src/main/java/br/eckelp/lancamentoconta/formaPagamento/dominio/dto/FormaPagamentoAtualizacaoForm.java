package br.eckelp.lancamentoconta.formaPagamento.dominio.dto;

import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
import lombok.Data;

@Data
public class FormaPagamentoAtualizacaoForm {

    private Integer id;

    private String descricao;

    public FormaPagamento criarFormaPagamento(Integer formaPagamentoId) {
        return FormaPagamento.builder()
                .id(formaPagamentoId)
                .descricao(descricao).build();
    }

    public FormaPagamento criarFormaPagamento(){
        return criarFormaPagamento(id);
    }
}
