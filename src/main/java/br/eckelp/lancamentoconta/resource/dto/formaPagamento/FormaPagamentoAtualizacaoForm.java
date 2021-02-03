package br.eckelp.lancamentoconta.resource.dto.formaPagamento;

import br.eckelp.lancamentoconta.domain.model.FormaPagamento;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class FormaPagamentoAtualizacaoForm {

    private Integer id;

    private String descricao;

    public FormaPagamento converter(Integer formaPagamentoId) {
        return FormaPagamento.builder()
                .id(formaPagamentoId)
                .descricao(descricao).build();
    }

    public FormaPagamento converter(){
        return converter(id);
    }
}
