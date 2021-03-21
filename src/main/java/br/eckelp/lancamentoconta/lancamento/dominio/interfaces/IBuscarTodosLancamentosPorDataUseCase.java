package br.eckelp.lancamentoconta.lancamento.dominio.interfaces;

import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;

import java.time.LocalDate;
import java.util.List;

public interface IBuscarTodosLancamentosPorDataUseCase {
    List<Lancamento> buscarTodos(LocalDate dataInicial, LocalDate dataFinal);
}
