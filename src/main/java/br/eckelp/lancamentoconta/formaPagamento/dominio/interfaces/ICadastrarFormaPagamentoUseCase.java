package br.eckelp.lancamentoconta.formaPagamento.dominio.interfaces;

import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formaPagamento.dominio.dto.FormaPagamentoCadastroForm;

public interface ICadastrarFormaPagamentoUseCase {

    FormaPagamento cadastrar(FormaPagamentoCadastroForm form);

}
