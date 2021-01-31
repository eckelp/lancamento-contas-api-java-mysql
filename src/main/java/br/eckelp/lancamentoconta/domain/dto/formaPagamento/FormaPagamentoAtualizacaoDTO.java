package br.eckelp.lancamentoconta.domain.dto.formaPagamento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoAtualizacaoDTO  implements IFormaPagamentoDTO {

    private Integer id;

    private String descricao;
}
