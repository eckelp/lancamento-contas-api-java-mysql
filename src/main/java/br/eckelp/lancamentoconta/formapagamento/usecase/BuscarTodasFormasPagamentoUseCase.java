package br.eckelp.lancamentoconta.formapagamento.usecase;

import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.formapagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formapagamento.dominio.interfaces.IBuscarTodasFormasPagamentoUseCase;
import br.eckelp.lancamentoconta.formapagamento.infra.IFormaPagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarTodasFormasPagamentoUseCase implements IBuscarTodasFormasPagamentoUseCase {

    private final IFormaPagamentoRepository repository;

    public BuscarTodasFormasPagamentoUseCase(IFormaPagamentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FormaPagamento> buscarTodas(Usuario usuario) {

        List<FormaPagamento> listaFormasPagamento = this.repository.listarFormasPagamento(usuario);

        return listaFormasPagamento;
    }
}
