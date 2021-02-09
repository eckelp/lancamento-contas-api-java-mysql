package br.eckelp.lancamentoconta.formaPagamento.dominio.dto;

import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormaPagamentoCadastroForm  {

    private Integer id;

    private String descricao;

    public FormaPagamento criarFormaPagamento() {
        return FormaPagamento.builder()
                .id(id)
                .descricao(descricao).build();
    }

    public FormaPagamentoCadastroForm(String descricao) {
        this.descricao = descricao;
    }
}
