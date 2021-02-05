package br.eckelp.lancamentoconta.lancamento.dominio.interfaces;

import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;
import br.eckelp.lancamentoconta.lancamento.dominio.dto.LancamentoAtualizacaoForm;

public interface IAtualizarLancamentoUseCase {

    Lancamento atualizar(Integer lancamentoId, LancamentoAtualizacaoForm form);

}
