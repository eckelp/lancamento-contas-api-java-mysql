package br.eckelp.lancamentoconta.lancamento.dominio.interfaces;

import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;
import br.eckelp.lancamentoconta.lancamento.dominio.dto.LancamentoCadastroForm;

public interface ICadastrarLancamentoUseCase {

    Lancamento cadastrar(LancamentoCadastroForm form);

}
