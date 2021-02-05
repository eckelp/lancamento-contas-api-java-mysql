package br.eckelp.lancamentoconta.formaPagamento.dominio.interfaces;

import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formaPagamento.dominio.dto.FormaPagamentoAtualizacaoForm;

public interface IAtualizarFormaPagamentoUseCase {

    FormaPagamento atualizar(Integer formaPagamentoId, FormaPagamentoAtualizacaoForm form);

}
