package br.eckelp.lancamentoconta.lancamento.dominio.interfaces;

import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;

public interface ISalvarLancamentoUseCase {

    Lancamento salvar(Lancamento lancamento);

}
