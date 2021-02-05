package br.eckelp.lancamentoconta.formaPagamento.dominio.dto;

import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
import lombok.Data;

@Data
public class FormaPagamentoCadastroForm  {

    private Integer id;

    private String descricao;

    public FormaPagamento criarFormaPagamento() {
        return FormaPagamento.builder()
                .id(id)
                .descricao(descricao).build();
    }
}
