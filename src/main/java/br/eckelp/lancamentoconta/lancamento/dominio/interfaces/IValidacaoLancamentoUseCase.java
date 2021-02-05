package br.eckelp.lancamentoconta.lancamento.dominio.interfaces;

import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;

public interface IValidacaoLancamentoUseCase {

    void validar(Lancamento lancamento);
}
