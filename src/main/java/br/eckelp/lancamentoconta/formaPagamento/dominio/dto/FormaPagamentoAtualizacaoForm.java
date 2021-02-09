package br.eckelp.lancamentoconta.formaPagamento.dominio.dto;

import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormaPagamentoAtualizacaoForm {

    private Integer id;

    private String descricao;

    public FormaPagamento criarFormaPagamento(Integer formaPagamentoId) {
        return FormaPagamento.builder()
                .id(formaPagamentoId)
                .descricao(descricao).build();
    }

    public FormaPagamentoAtualizacaoForm(String descricao) {
        this.descricao = descricao;
    }

    public FormaPagamento criarFormaPagamento(){
        return criarFormaPagamento(id);
    }
}
