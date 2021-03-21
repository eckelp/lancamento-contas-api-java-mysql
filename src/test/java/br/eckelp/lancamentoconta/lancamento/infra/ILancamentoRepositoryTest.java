package br.eckelp.lancamentoconta.lancamento.infra;

import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.app.security.repository.IUsuarioRepository;
import br.eckelp.lancamentoconta.app.security.service.EncoderService;
import br.eckelp.lancamentoconta.categoria.CategoriaCenarioTest;
import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.infra.ICategoriaRepository;
import br.eckelp.lancamentoconta.formapagamento.FormaPagamentoCenarioTest;
import br.eckelp.lancamentoconta.formapagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formapagamento.infra.IFormaPagamentoRepository;
import br.eckelp.lancamentoconta.lancamento.LancamentoCenarioTest;
import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;
import br.eckelp.lancamentoconta.usuario.UsuarioCenarioTest;
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
    ICategoriaRepository categoriaRepository;
    @Autowired
    IFormaPagamentoRepository formaPagamentoRepository;
    @Autowired
    private EncoderService encoderService;
    @Autowired
    private IUsuarioRepository usuarioRepository;


    private UsuarioCenarioTest cenarioUsuario;
    private LancamentoCenarioTest cenario;
    private CategoriaCenarioTest cenarioCategoria;
    private FormaPagamentoCenarioTest cenarioFormaPagamento;

    @Before
    public void cenario () {
        this.cenario = new LancamentoCenarioTest(repository, categoriaRepository, formaPagamentoRepository);
        this.cenarioCategoria = new CategoriaCenarioTest(categoriaRepository);
        this.cenarioFormaPagamento = new FormaPagamentoCenarioTest(formaPagamentoRepository);
        this.cenarioUsuario = new UsuarioCenarioTest(encoderService, usuarioRepository);
    }


    @Test
    public void deveriaCarregarListaDeFormasPagamento() {
        Usuario usuario = this.cenarioUsuario.getUsuario();
        this.cenario.criarLancamentosValidos(usuario);

        List<Lancamento> lancamentos = repository.listarLancamentos(LocalDate.now().minusDays(1L), LocalDate.now().plusDays(1L));

        Assert.assertFalse(lancamentos.isEmpty());

    }

    @Test
    public void deveriaCarregarUmLancamento() {
        this.cenario.inicializarCenarioVazio();
        Usuario usuario = this.cenarioUsuario.getUsuario();
        Categoria mercado = this.cenarioCategoria.criarUmaCategoria("Mercado", usuario);
        FormaPagamento dinheiro = this.cenarioFormaPagamento.criarFormaPagamento("Dinheiro", usuario);

        Lancamento lancamento = Lancamento.builder()
                .categoria(mercado)
                .formaPagamento(dinheiro)
                .descricao("Novo Lançamento")
                .valor(BigDecimal.TEN)
                .data(LocalDate.now())
                .usuario(usuario)
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
