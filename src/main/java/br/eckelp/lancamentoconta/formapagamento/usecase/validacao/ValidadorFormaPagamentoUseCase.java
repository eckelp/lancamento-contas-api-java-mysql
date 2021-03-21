package br.eckelp.lancamentoconta.formapagamento.usecase.validacao;

import br.eckelp.lancamentoconta.formapagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formapagamento.dominio.interfaces.IValidacaoFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formapagamento.dominio.interfaces.IValidadorFormaPagamentoUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidadorFormaPagamentoUseCase implements IValidadorFormaPagamentoUseCase {

    private final List<IValidacaoFormaPagamentoUseCase> validacoes;

    public ValidadorFormaPagamentoUseCase(List<IValidacaoFormaPagamentoUseCase> validacoes) {
        this.validacoes = validacoes;
    }

    @Override
    public void executar(FormaPagamento formaPagamento){
        validacoes.forEach(validacao -> validacao.validar(formaPagamento));
    }
}
