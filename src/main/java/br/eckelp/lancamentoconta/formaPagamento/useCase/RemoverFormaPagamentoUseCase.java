package br.eckelp.lancamentoconta.formaPagamento.useCase;

import br.eckelp.lancamentoconta.formaPagamento.dominio.interfaces.IRemoverFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formaPagamento.infra.IFormaPagamentoRepository;
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
