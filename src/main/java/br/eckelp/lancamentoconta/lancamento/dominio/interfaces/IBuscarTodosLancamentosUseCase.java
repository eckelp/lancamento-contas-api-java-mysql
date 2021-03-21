package br.eckelp.lancamentoconta.lancamento.dominio.interfaces;

import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;

import java.util.List;

public interface IBuscarTodosLancamentosUseCase {
    List<Lancamento> buscarTodos(Usuario usuario);

}
