package br.eckelp.lancamentoconta.formaPagamento.dominio.interfaces;

import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;

public interface ISalvarFormaPagamentoUseCase {

    FormaPagamento salvar(FormaPagamento formaPagamento);
}
