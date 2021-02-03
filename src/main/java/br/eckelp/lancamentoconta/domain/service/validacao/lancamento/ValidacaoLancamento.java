package br.eckelp.lancamentoconta.domain.service.validacao.lancamento;

import br.eckelp.lancamentoconta.domain.model.Lancamento;

public interface ValidacaoLancamento {

    void validar(Lancamento lancamento);
}
