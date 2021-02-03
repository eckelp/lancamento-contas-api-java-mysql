package br.eckelp.lancamentoconta.resource.dto.formaPagamento;

import br.eckelp.lancamentoconta.domain.model.FormaPagamento;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class FormaPagamentoDto {

    private Integer id;

    private String descricao;

    public FormaPagamentoDto(FormaPagamento formaPagamento) {
        this.id = formaPagamento.getId();
        this.descricao = formaPagamento.getDescricao();
    }
}
