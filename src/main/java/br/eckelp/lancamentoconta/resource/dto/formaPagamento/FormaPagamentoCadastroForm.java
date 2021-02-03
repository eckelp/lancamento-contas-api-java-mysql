package br.eckelp.lancamentoconta.resource.dto.formaPagamento;

import br.eckelp.lancamentoconta.domain.model.FormaPagamento;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class FormaPagamentoCadastroForm  {

    private Integer id;

    private String descricao;

    public FormaPagamento converter() {
        return FormaPagamento.builder()
                .id(id)
                .descricao(descricao).build();
    }
}
