package br.eckelp.lancamentoconta.lancamento.dominio.interfaces;

import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;

public interface IValidadorLancamentoUseCase {

    void executar(Lancamento lancamento);
}
