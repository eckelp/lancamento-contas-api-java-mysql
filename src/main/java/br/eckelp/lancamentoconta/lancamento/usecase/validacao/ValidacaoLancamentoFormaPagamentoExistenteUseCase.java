package br.eckelp.lancamentoconta.lancamento.usecase.validacao;

import br.eckelp.lancamentoconta.formapagamento.dominio.interfaces.IBuscarUmaFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;
import br.eckelp.lancamentoconta.lancamento.dominio.interfaces.IValidacaoLancamentoUseCase;
import org.springframework.stereotype.Service;

@Service
public class ValidacaoLancamentoFormaPagamentoExistenteUseCase implements IValidacaoLancamentoUseCase {

    private final IBuscarUmaFormaPagamentoUseCase buscarFormaPagamentoUseCase;

    public ValidacaoLancamentoFormaPagamentoExistenteUseCase(IBuscarUmaFormaPagamentoUseCase buscarFormaPagamentoUseCase) {
        this.buscarFormaPagamentoUseCase = buscarFormaPagamentoUseCase;
    }

    @Override
    public void validar(Lancamento lancamento) {
        this.buscarFormaPagamentoUseCase.porId(lancamento.getFormaPagamentoId());
    }
}
