package br.eckelp.lancamentoconta.lancamento;

import br.eckelp.lancamentoconta.categoria.CategoriaCenarioTest;
import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.infra.ICategoriaRepository;
import br.eckelp.lancamentoconta.formaPagamento.FormaPagamentoCenarioTest;
import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formaPagamento.infra.IFormaPagamentoRepository;
import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;
import br.eckelp.lancamentoconta.lancamento.infra.ILancamentoRepository;
import org.apache.tomcat.jni.Local;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LancamentoCenarioTest {

    private final ILancamentoRepository repository;
    private final ICategoriaRepository categoriaRepository;
    private final IFormaPagamentoRepository formaPagamentoRepository;


    public LancamentoCenarioTest(ILancamentoRepository repository, ICategoriaRepository categoriaRepository, IFormaPagamentoRepository formaPagamentoRepository) {
        this.repository = repository;
        this.categoriaRepository = categoriaRepository;
        this.formaPagamentoRepository = formaPagamentoRepository;
        this.inicializarCenarioVazio();
    }

    public LancamentoCenarioTest(ILancamentoRepository repository){
        this.repository = repository;
        this.categoriaRepository = null;
        this.formaPagamentoRepository = null;
    }



    public Lancamento criarLancamento(String descricao){
        Categoria mercado = new CategoriaCenarioTest(categoriaRepository).criarUmaCategoria("Mercado");
        FormaPagamento dinheiro = new FormaPagamentoCenarioTest(formaPagamentoRepository).criarFormaPagamento("Dinheiro");

       return criarLancamento(descricao, mercado, dinheiro);
    }

    private Lancamento criarLancamento(String descricao, Categoria categoria, FormaPagamento formaPagamento){
        Lancamento lancamento = Lancamento.builder()
                .descricao(descricao)
                .categoria(categoria)
                .formaPagamento(formaPagamento)
                .valor(BigDecimal.TEN)
                .data(LocalDate.now())
                .build();

        return repository.save(lancamento);
    }

    public void inicializarCenarioVazio(){
        repository.deleteAll();
    }

    public void criarLancamentosValidos() {
        Categoria mercado = new CategoriaCenarioTest(categoriaRepository).criarUmaCategoria("Mercado");
        FormaPagamento dinheiro = new FormaPagamentoCenarioTest(formaPagamentoRepository).criarFormaPagamento("Dinheiro");
        this.criarLancamento("Novo Lançamento 1", mercado, dinheiro);
        this.criarLancamento("Novo Lançamento 2", mercado, dinheiro);
    }
}
