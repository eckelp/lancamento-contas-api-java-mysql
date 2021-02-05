package br.eckelp.lancamentoconta.lancamento.useCase;

import br.eckelp.lancamentoconta.lancamento.dominio.interfaces.IRemoverLancamentoUseCase;
import br.eckelp.lancamentoconta.lancamento.infra.ILancamentoRepository;
import org.springframework.stereotype.Service;

@Service
public class RemoverLancamentoService implements IRemoverLancamentoUseCase {

    private final ILancamentoRepository repository;

    public RemoverLancamentoService(ILancamentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void remover(Integer lancamentoId) {
        this.repository.deleteById(lancamentoId);
    }
}
