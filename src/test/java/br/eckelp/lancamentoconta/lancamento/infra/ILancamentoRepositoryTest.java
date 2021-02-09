package br.eckelp.lancamentoconta.lancamento.infra;

import br.eckelp.lancamentoconta.categoria.CategoriaCenarioTest;
import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.infra.ICategoriaRepository;
import br.eckelp.lancamentoconta.formaPagamento.FormaPagamentoCenarioTest;
import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formaPagamento.infra.IFormaPagamentoRepository;
import br.eckelp.lancamentoconta.lancamento.LancamentoCenarioTest;
import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ILancamentoRepositoryTest {

    @Autowired
    private ILancamentoRepository repository;

    @Autowired
    private ICategoriaRepository categoriaRepository;

    @Autowired
    private IFormaPagamentoRepository formaPagamentoRepository;


    @Test
    public void deveriaCarregarListaDeFormasPagamento() {
        new LancamentoCenarioTest(repository, categoriaRepository, formaPagamentoRepository).criarLancamentosValidos();

        List<Lancamento> lancamentos = repository.listarLancamentos(LocalDate.now().minusDays(1L), LocalDate.now().plusDays(1L));

        Assert.assertFalse(lancamentos.isEmpty());

    }

    @Test
    public void deveriaCarregarUmLancamento() {
        new LancamentoCenarioTest(repository).inicializarCenarioVazio();
        Categoria mercado = new CategoriaCenarioTest(categoriaRepository).criarUmaCategoria("Mercado");
        FormaPagamento dinheiro = new FormaPagamentoCenarioTest(formaPagamentoRepository).criarFormaPagamento("Dinheiro");

        Lancamento lancamento = Lancamento.builder()
                .categoria(mercado)
                .formaPagamento(dinheiro)
                .descricao("Novo Lançamento")
                .valor(BigDecimal.TEN)
                .data(LocalDate.now())
                .build();

        Lancamento lancamentoCadastrado = repository.save(lancamento);

        Optional<Lancamento> lancamentoOptional = repository.findById(lancamentoCadastrado.getId());

        Assert.assertTrue(lancamentoOptional.isPresent());
    }

    @Test
    public void naoDeveriaCarregarUmLancamentoInvalido() {
        Optional<Lancamento> lancamento = repository.findById(-1);

        Assert.assertTrue(lancamento.isEmpty());
    }

}
