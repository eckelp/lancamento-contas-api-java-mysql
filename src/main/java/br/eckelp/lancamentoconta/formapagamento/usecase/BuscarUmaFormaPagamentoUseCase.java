package br.eckelp.lancamentoconta.formapagamento.usecase;

import br.eckelp.lancamentoconta.formapagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formapagamento.dominio.interfaces.IBuscarUmaFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formapagamento.infra.IFormaPagamentoRepository;
import br.eckelp.lancamentoconta.app.exception.ObjetoNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarUmaFormaPagamentoUseCase implements IBuscarUmaFormaPagamentoUseCase {

    private final IFormaPagamentoRepository repository;

    public BuscarUmaFormaPagamentoUseCase(IFormaPagamentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public FormaPagamento porId(Integer formaPagamentoId)  throws ObjetoNaoEncontradoException {

        if(formaPagamentoId == null){
            throw new ObjetoNaoEncontradoException("Forma de pagamento não encontrada");
        }

        Optional<FormaPagamento> formaPagamentoOptional = this.repository.findById(formaPagamentoId);

        return formaPagamentoOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Forma de pagamento não encontrada"));
    }

}
