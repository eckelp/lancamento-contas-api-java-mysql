package br.eckelp.lancamentoconta.formaPagamento.dominio.interfaces;

import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;

public interface IBuscarUmaFormaPagamentoUseCase {

    FormaPagamento porId(Integer id);

}
