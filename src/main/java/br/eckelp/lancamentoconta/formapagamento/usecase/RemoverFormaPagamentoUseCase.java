package br.eckelp.lancamentoconta.formapagamento.usecase;

import br.eckelp.lancamentoconta.formapagamento.dominio.interfaces.IRemoverFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formapagamento.infra.IFormaPagamentoRepository;
import org.springframework.stereotype.Service;

@Service
public class RemoverFormaPagamentoUseCase implements IRemoverFormaPagamentoUseCase {

    private final IFormaPagamentoRepository repository;

    public RemoverFormaPagamentoUseCase(IFormaPagamentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void remover(Integer formaPagamentoId) {
        this.repository.deleteById(formaPagamentoId);
    }
}
