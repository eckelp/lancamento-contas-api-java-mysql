package br.eckelp.lancamentoconta.lancamento.usecase;

import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;
import br.eckelp.lancamentoconta.lancamento.dominio.interfaces.IBuscarTodosLancamentosPorDataUseCase;
import br.eckelp.lancamentoconta.lancamento.infra.ILancamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BuscarTodosLancamentoPorDataUseCase implements IBuscarTodosLancamentosPorDataUseCase {

    private final ILancamentoRepository repository;

    public BuscarTodosLancamentoPorDataUseCase(ILancamentoRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Lancamento> buscarTodos(LocalDate dataInicial, LocalDate dataFinal) {

        List<Lancamento> listaLancamentos = this.repository.listarLancamentos(dataInicial, dataFinal);

        return listaLancamentos;
    }
}
