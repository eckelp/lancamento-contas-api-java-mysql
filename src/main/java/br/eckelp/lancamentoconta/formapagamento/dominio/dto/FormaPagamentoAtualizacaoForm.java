package br.eckelp.lancamentoconta.formapagamento.dominio.dto;

import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.formapagamento.dominio.FormaPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormaPagamentoAtualizacaoForm {

    private Integer id;

    private String descricao;

    public FormaPagamento criarFormaPagamento(Integer formaPagamentoId, Usuario usuario) {
        return FormaPagamento.builder()
                .id(formaPagamentoId)
                .descricao(descricao)
                .usuario(usuario)
                .build();
    }

    public FormaPagamentoAtualizacaoForm(String descricao) {
        this.descricao = descricao;
    }

}
