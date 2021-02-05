package br.eckelp.lancamentoconta.formaPagamento.useCase;

import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formaPagamento.dominio.interfaces.IBuscarTodasFormasPagamentoUseCase;
import br.eckelp.lancamentoconta.formaPagamento.infra.IFormaPagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarTodasFormasPagamentoUseCase implements IBuscarTodasFormasPagamentoUseCase {

    private final IFormaPagamentoRepository repository;

    public BuscarTodasFormasPagamentoUseCase(IFormaPagamentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FormaPagamento> buscarTodas() {

        List<FormaPagamento> listaFormasPagamento = this.repository.listarFormasPagamento();

        return listaFormasPagamento;
    }
}
