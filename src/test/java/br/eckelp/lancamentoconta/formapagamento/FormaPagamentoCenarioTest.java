package br.eckelp.lancamentoconta.formapagamento;

import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.formapagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formapagamento.infra.IFormaPagamentoRepository;

public class FormaPagamentoCenarioTest {

    private final IFormaPagamentoRepository repository;

    public FormaPagamentoCenarioTest(IFormaPagamentoRepository repository) {
        this.repository = repository;
        this.inicializarCenarioVazio();
    }

    public void criarListaFormasPagamentoValidas(Usuario usuario) {
        repository.save(new FormaPagamento("Dinheiro", usuario));
        repository.save(new FormaPagamento("Crédito", usuario));
    }

    public void inicializarCenarioVazio(){
        repository.deleteAll();
    }

    public FormaPagamento criarFormaPagamento(String descricao, Usuario usuario) {
        return repository.save(new FormaPagamento(descricao, usuario));
    }
}
