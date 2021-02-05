package br.eckelp.lancamentoconta.lancamento.dominio.interfaces;

import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;

public interface IBuscarUmLancamentoUseCase {


    Lancamento porId(Integer lancamentoId);
}
