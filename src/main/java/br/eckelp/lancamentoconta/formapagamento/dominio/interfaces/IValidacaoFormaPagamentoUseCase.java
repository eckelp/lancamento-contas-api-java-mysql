package br.eckelp.lancamentoconta.formapagamento.dominio.interfaces;

import br.eckelp.lancamentoconta.formapagamento.dominio.FormaPagamento;

public interface IValidacaoFormaPagamentoUseCase {

    void validar(FormaPagamento formaPagamento);

}
