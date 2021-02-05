package br.eckelp.lancamentoconta.lancamento.useCase.validacao;

import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;
import br.eckelp.lancamentoconta.lancamento.dominio.interfaces.IValidacaoLancamentoUseCase;
import br.eckelp.lancamentoconta.sistema.infra.exception.ArgumentoInvalidoException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ValidacaoLancamentoValorMaiorQueZeroUseCase implements IValidacaoLancamentoUseCase {

    @Override
    public void validar(Lancamento lancamento) {

        BigDecimal novoValor = lancamento.getValor();

        if(BigDecimal.ZERO.compareTo(novoValor) >= 0){
            throw new ArgumentoInvalidoException("O valor do lançamento deve ser maior que zero");
        }

    }
}
