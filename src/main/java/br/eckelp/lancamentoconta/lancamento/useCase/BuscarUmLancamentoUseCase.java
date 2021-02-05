package br.eckelp.lancamentoconta.lancamento.useCase;

import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;
import br.eckelp.lancamentoconta.lancamento.dominio.interfaces.IBuscarUmLancamentoUseCase;
import br.eckelp.lancamentoconta.lancamento.infra.ILancamentoRepository;
import br.eckelp.lancamentoconta.sistema.infra.exception.ObjetoNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarUmLancamentoUseCase implements IBuscarUmLancamentoUseCase {

    private final ILancamentoRepository repository;

    public BuscarUmLancamentoUseCase(ILancamentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Lancamento porId(Integer lancamentoId) {
        Optional<Lancamento> lancamentoOptional = this.repository.findById(lancamentoId);

        return lancamentoOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Lançamento não encontrado"));
    }

}
