package br.eckelp.lancamentoconta.service.validacao.lancamento;

import br.eckelp.lancamentoconta.domain.model.Lancamento;
import br.eckelp.lancamentoconta.service.FormaPagamentoService;
import org.springframework.stereotype.Service;

@Service
public class ValidacaoLancamentoFormaPagamentoExistenteService implements ValidacaoLancamento {

    private final FormaPagamentoService formaPagamentoService;

    public ValidacaoLancamentoFormaPagamentoExistenteService(FormaPagamentoService formaPagamentoService) {
        this.formaPagamentoService = formaPagamentoService;
    }

    @Override
    public void validar(Lancamento lancamento) {
        this.formaPagamentoService.buscarFormaPagamentoPorId(lancamento.getFormaPagamentoId());
    }
}
