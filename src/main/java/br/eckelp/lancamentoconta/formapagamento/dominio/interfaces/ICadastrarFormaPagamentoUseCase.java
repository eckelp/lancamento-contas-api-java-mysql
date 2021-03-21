package br.eckelp.lancamentoconta.formapagamento.dominio.interfaces;

import br.eckelp.lancamentoconta.formapagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formapagamento.dominio.dto.FormaPagamentoCadastroForm;

public interface ICadastrarFormaPagamentoUseCase {

    FormaPagamento cadastrar(FormaPagamentoCadastroForm form);

}
