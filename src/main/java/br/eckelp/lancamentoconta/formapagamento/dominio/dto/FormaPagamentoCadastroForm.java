package br.eckelp.lancamentoconta.formapagamento.dominio.dto;

import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.formapagamento.dominio.FormaPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormaPagamentoCadastroForm  {

    private Integer id;

    private String descricao;

    public FormaPagamento criarFormaPagamento(Usuario usuario) {
        return FormaPagamento.builder()
                .id(id)
                .descricao(descricao)
                .usuario(usuario)
                .build();
    }

    public FormaPagamentoCadastroForm(String descricao) {
        this.descricao = descricao;
    }
}
