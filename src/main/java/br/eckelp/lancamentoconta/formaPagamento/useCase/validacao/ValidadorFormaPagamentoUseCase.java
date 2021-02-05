package br.eckelp.lancamentoconta.formaPagamento.useCase.validacao;

import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formaPagamento.dominio.interfaces.IValidacaoFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formaPagamento.dominio.interfaces.IValidadorFormaPagamentoUseCase;
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
