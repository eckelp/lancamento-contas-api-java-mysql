package br.eckelp.lancamentoconta.domain.dto.formaPagamento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormaPagamentoListagemDTO {

    private Integer id;

    private String descricao;
}
