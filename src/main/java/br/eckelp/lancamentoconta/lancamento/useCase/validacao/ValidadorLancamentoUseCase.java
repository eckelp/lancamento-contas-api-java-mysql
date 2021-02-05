package br.eckelp.lancamentoconta.lancamento.useCase.validacao;

import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;
import br.eckelp.lancamentoconta.lancamento.dominio.interfaces.IValidacaoLancamentoUseCase;
import br.eckelp.lancamentoconta.lancamento.dominio.interfaces.IValidadorLancamentoUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidadorLancamentoUseCase implements IValidadorLancamentoUseCase {

    private final List<IValidacaoLancamentoUseCase> validacoes;

    public ValidadorLancamentoUseCase(List<IValidacaoLancamentoUseCase> validacoes) {
        this.validacoes = validacoes;
    }

    @Override
    public void executar(Lancamento lancamento) {
        validacoes.forEach(validacao -> validacao.validar(lancamento));
    }
}
