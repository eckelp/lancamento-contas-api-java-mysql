package br.eckelp.lancamentoconta.service.validacao;

import br.eckelp.lancamentoconta.domain.exception.ArgumentoInvalidoException;
import br.eckelp.lancamentoconta.domain.model.Lancamento;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ValidacaoLancamentoValorMaiorQueZeroService implements ValidacaoLancamento {

    @Override
    public void validar(Lancamento lancamento) {

        BigDecimal novoValor = lancamento.getValor();

        if(BigDecimal.ZERO.compareTo(novoValor) >= 0){
            throw new ArgumentoInvalidoException("O valor do lançamento deve ser maior que zero");
        }

    }
}
