package br.eckelp.lancamentoconta.formaPagamento.dominio.interfaces;

import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;

import java.util.List;

public interface IBuscarTodasFormasPagamentoUseCase {

    List<FormaPagamento> buscarTodas();

}
