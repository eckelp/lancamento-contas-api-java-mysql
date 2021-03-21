package br.eckelp.lancamentoconta.lancamento.usecase.validacao;

import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;
import br.eckelp.lancamentoconta.lancamento.dominio.interfaces.IValidacaoLancamentoUseCase;
import br.eckelp.lancamentoconta.app.exception.ArgumentoInvalidoException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class ValidacaoLancamentoValorMaiorQueZeroUseCase implements IValidacaoLancamentoUseCase {

    @Override
    public void validar(Lancamento lancamento) {

        BigDecimal novoValor = lancamento.getValor();

        if(Objects.isNull(novoValor) || BigDecimal.ZERO.compareTo(novoValor) >= 0){
            throw new ArgumentoInvalidoException("O valor do lançamento deve ser maior que zero");
        }

    }
}
