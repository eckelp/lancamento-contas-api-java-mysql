package br.eckelp.lancamentoconta.lancamento.usecase;

import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;
import br.eckelp.lancamentoconta.lancamento.dominio.interfaces.IBuscarTodosLancamentosUseCase;
import br.eckelp.lancamentoconta.lancamento.infra.ILancamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarTodosLancamentosUseCase implements IBuscarTodosLancamentosUseCase {

    private final ILancamentoRepository repository;

    public BuscarTodosLancamentosUseCase(ILancamentoRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Lancamento> buscarTodos(Usuario usuario) {

        List<Lancamento> listaLancamentos = this.repository.listarLancamentos(usuario);

        return listaLancamentos;
    }
}
