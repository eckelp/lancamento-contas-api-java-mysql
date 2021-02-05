package br.eckelp.lancamentoconta.formaPagamento.dominio.interfaces;

import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;

public interface IValidadorFormaPagamentoUseCase {

    void executar(FormaPagamento formaPagamento);

}
