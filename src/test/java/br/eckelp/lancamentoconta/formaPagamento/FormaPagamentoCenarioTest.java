package br.eckelp.lancamentoconta.formaPagamento;

import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formaPagamento.infra.IFormaPagamentoRepository;

public class FormaPagamentoCenarioTest {

    private final IFormaPagamentoRepository repository;

    public FormaPagamentoCenarioTest(IFormaPagamentoRepository repository) {
        this.repository = repository;
        this.inicializarCenarioVazio();
    }

    public void criarListaFormasPagamentoValidas() {
        repository.save(new FormaPagamento("Dinheiro"));
        repository.save(new FormaPagamento("Crédito"));
    }

    public void inicializarCenarioVazio(){
        repository.deleteAll();
    }

    public FormaPagamento criarFormaPagamento(String descricao) {
        return repository.save(new FormaPagamento(descricao));
    }
}
