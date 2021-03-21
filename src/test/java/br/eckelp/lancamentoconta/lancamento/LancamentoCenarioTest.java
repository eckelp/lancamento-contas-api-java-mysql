package br.eckelp.lancamentoconta.lancamento;

import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.categoria.CategoriaCenarioTest;
import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.infra.ICategoriaRepository;
import br.eckelp.lancamentoconta.formapagamento.FormaPagamentoCenarioTest;
import br.eckelp.lancamentoconta.formapagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formapagamento.infra.IFormaPagamentoRepository;
import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;
import br.eckelp.lancamentoconta.lancamento.infra.ILancamentoRepository;

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



    public Lancamento criarLancamento(String descricao, Usuario usuario){
        Categoria mercado = new CategoriaCenarioTest(categoriaRepository).criarUmaCategoria("Mercado", usuario);
        FormaPagamento dinheiro = new FormaPagamentoCenarioTest(formaPagamentoRepository).criarFormaPagamento("Dinheiro", usuario);

       return criarLancamento(descricao, usuario, mercado, dinheiro);
    }

    private Lancamento criarLancamento(String descricao, Usuario usuario, Categoria categoria, FormaPagamento formaPagamento){
        Lancamento lancamento = Lancamento.builder()
                .descricao(descricao)
                .categoria(categoria)
                .formaPagamento(formaPagamento)
                .valor(BigDecimal.TEN)
                .data(LocalDate.now())
                .usuario(usuario)
                .build();

        return repository.save(lancamento);
    }

    public void inicializarCenarioVazio(){
        repository.deleteAll();
    }

    public void criarLancamentosValidos(Usuario usuario) {
        Categoria mercado = new CategoriaCenarioTest(categoriaRepository).criarUmaCategoria("Mercado", usuario);
        FormaPagamento dinheiro = new FormaPagamentoCenarioTest(formaPagamentoRepository).criarFormaPagamento("Dinheiro", usuario);

        this.criarLancamento("Novo Lançamento 1", usuario, mercado, dinheiro);
        this.criarLancamento("Novo Lançamento 2", usuario, mercado, dinheiro);
    }
}
