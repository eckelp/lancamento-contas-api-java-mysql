package br.eckelp.lancamentoconta.formapagamento.dominio.interfaces;

import br.eckelp.lancamentoconta.formapagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formapagamento.dominio.dto.FormaPagamentoAtualizacaoForm;

public interface IAtualizarFormaPagamentoUseCase {

    FormaPagamento atualizar(Integer formaPagamentoId, FormaPagamentoAtualizacaoForm form);

}
