package br.eckelp.lancamentoconta.lancamento.controller;

import br.eckelp.lancamentoconta.RequestCenarioTest;
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
import br.eckelp.lancamentoconta.lancamento.dominio.dto.LancamentoAtualizacaoForm;
import br.eckelp.lancamentoconta.lancamento.dominio.dto.LancamentoCadastroForm;
import br.eckelp.lancamentoconta.lancamento.infra.ILancamentoRepository;
import br.eckelp.lancamentoconta.usuario.UsuarioCenarioTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ILancamentoRepository repository;
    @Autowired
    private ICategoriaRepository categoriaRepository;
    @Autowired
    private IFormaPagamentoRepository formaPagamentoRepository;
    @Autowired
    private EncoderService encoderService;
    @Autowired
    private IUsuarioRepository usuarioRepository;

    private LancamentoCenarioTest cenario;
    private CategoriaCenarioTest cenarioCategoria;
    private FormaPagamentoCenarioTest cenarioFormaPagamento;
    private UsuarioCenarioTest cenarioUsuario;

    @Before
    public void cenario() {
        this.cenario = new LancamentoCenarioTest(repository, categoriaRepository, formaPagamentoRepository);
        this.cenarioFormaPagamento = new FormaPagamentoCenarioTest(formaPagamentoRepository);
        this.cenarioCategoria = new CategoriaCenarioTest(categoriaRepository);
        this.cenarioUsuario = new UsuarioCenarioTest(encoderService, usuarioRepository);
    }

    @Test
    public void deveCriarUmLancamento() throws Exception {
        this.cenario.inicializarCenarioVazio();

        Usuario usuario = this.cenarioUsuario.getUsuario();

        Categoria mercado = this.cenarioCategoria.criarUmaCategoria("Mercado", usuario);
        FormaPagamento dinheiro = this.cenarioFormaPagamento.criarFormaPagamento("Dinheiro", usuario);
        URI uri = new URI("/lancamentos");

        LancamentoCadastroForm form = LancamentoCadastroForm.builder()
                .descricao("Lançamento de uma conta")
                .categoriaId(mercado.getId())
                .formaPagamentoId(dinheiro.getId())
                .valor(BigDecimal.valueOf(120.50))
                .data(LocalDate.now())
                .build();

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post(uri)
                                .content(new ObjectMapper().writeValueAsString(form))
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(headers(usuario))
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.descricao").value(form.getDescricao()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.valor").value(form.getValor()));
    }

    @Test
    public void naoDeveriaCriarLancamentoComDescricaoVazia() throws Exception{
        this.cenario.inicializarCenarioVazio();
        Usuario usuario = this.cenarioUsuario.getUsuario();
        Categoria mercado = this.cenarioCategoria.criarUmaCategoria("Mercado", usuario);
        FormaPagamento dinheiro = this.cenarioFormaPagamento.criarFormaPagamento("Dinheiro", usuario);

        URI uri = new URI("/lancamentos");

        LancamentoCadastroForm form = LancamentoCadastroForm.builder()
                .descricao("")
                .categoriaId(mercado.getId())
                .formaPagamentoId(dinheiro.getId())
                .valor(BigDecimal.valueOf(120.50))
                .data(LocalDate.now())
                .build();
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post(uri)
                                .content(new ObjectMapper().writeValueAsString(form))
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(headers(usuario))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void naoDeveriaCriarLancamentoSemCategoria() throws Exception{
        this.cenario.inicializarCenarioVazio();
        Usuario usuario = this.cenarioUsuario.getUsuario();
        FormaPagamento dinheiro = this.cenarioFormaPagamento.criarFormaPagamento("Dinheiro", usuario);

        URI uri = new URI("/lancamentos");

        LancamentoCadastroForm form = LancamentoCadastroForm.builder()
                .descricao("Lançamento sem categoria")
                .formaPagamentoId(dinheiro.getId())
                .valor(BigDecimal.valueOf(120.50))
                .data(LocalDate.now())
                .build();
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post(uri)
                                .content(new ObjectMapper().writeValueAsString(form))
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(headers(usuario))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void naoDeveriaCriarLancamentoSemFormaPagamento() throws Exception{
        this.cenario.inicializarCenarioVazio();
        Usuario usuario = this.cenarioUsuario.getUsuario();

        Categoria mercado = this.cenarioCategoria.criarUmaCategoria("Mercado", usuario);

        URI uri = new URI("/lancamentos");

        LancamentoCadastroForm form = LancamentoCadastroForm.builder()
                .descricao("Lançamento sem categoria")
                .categoriaId(mercado.getId())
                .valor(BigDecimal.valueOf(120.50))
                .data(LocalDate.now())
                .build();
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post(uri)
                                .content(new ObjectMapper().writeValueAsString(form))
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(headers(usuario))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void naoDeveriaCriarLancamentoSemValor() throws Exception{
        this.cenario.inicializarCenarioVazio();
        Usuario usuario = this.cenarioUsuario.getUsuario();


        Categoria mercado = this.cenarioCategoria.criarUmaCategoria("Mercado", usuario);
        FormaPagamento dinheiro = this.cenarioFormaPagamento.criarFormaPagamento("Dinheiro", usuario);

        URI uri = new URI("/lancamentos");

        LancamentoCadastroForm form = LancamentoCadastroForm.builder()
                .descricao("Lançamento sem categoria")
                .categoriaId(mercado.getId())
                .formaPagamentoId(dinheiro.getId())
                .data(LocalDate.now())
                .build();
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post(uri)
                                .content(new ObjectMapper().writeValueAsString(form))
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(headers(usuario))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void naoDeveriaCriarLancamentoComValorZerado() throws Exception{
        this.cenario.inicializarCenarioVazio();
        Usuario usuario = this.cenarioUsuario.getUsuario();

        Categoria mercado = this.cenarioCategoria.criarUmaCategoria("Mercado", usuario);
        FormaPagamento dinheiro = this.cenarioFormaPagamento.criarFormaPagamento("Dinheiro", usuario);

        URI uri = new URI("/lancamentos");

        LancamentoCadastroForm form = LancamentoCadastroForm.builder()
                .descricao("Lançamento sem categoria")
                .categoriaId(mercado.getId())
                .formaPagamentoId(dinheiro.getId())
                .data(LocalDate.now())
                .valor(BigDecimal.ZERO)
                .build();
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post(uri)
                                .content(new ObjectMapper().writeValueAsString(form))
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(headers(usuario))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void naoDeveriaCriarLancamentoComValorNegativo() throws Exception{
        this.cenario.inicializarCenarioVazio();
        Usuario usuario = this.cenarioUsuario.getUsuario();

        Categoria mercado = this.cenarioCategoria.criarUmaCategoria("Mercado", usuario);
        FormaPagamento dinheiro = this.cenarioFormaPagamento.criarFormaPagamento("Dinheiro", usuario);

        URI uri = new URI("/lancamentos");

        LancamentoCadastroForm form = LancamentoCadastroForm.builder()
                .descricao("Lançamento sem categoria")
                .categoriaId(mercado.getId())
                .formaPagamentoId(dinheiro.getId())
                .data(LocalDate.now())
                .valor(BigDecimal.TEN.negate())
                .build();
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post(uri)
                                .content(new ObjectMapper().writeValueAsString(form))
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(headers(usuario))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void deveriaAtualizarUmLancamento() throws Exception {
        Usuario usuario = this.cenarioUsuario.getUsuario();
        Lancamento lancamentoOriginal = this.cenario.criarLancamento("Lançamento para atualizar", usuario);

        LancamentoAtualizacaoForm form = LancamentoAtualizacaoForm.builder()
                .categoriaId(lancamentoOriginal.getCategoriaId())
                .formaPagamentoId(lancamentoOriginal.getFormaPagamentoId())
                .descricao("Nova descrição")
                .data(lancamentoOriginal.getData())
                .valor(BigDecimal.valueOf(110L))
                .build();

        URI uri = new URI("/lancamentos/" + lancamentoOriginal.getId());
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put(uri)
                                .content(new ObjectMapper().writeValueAsString(form))
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(headers(usuario))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.descricao").value(form.getDescricao()));

    }

    @Test
    public void naoDeveriaAtualizarUmLancamentoSemDescricao() throws Exception {
        Usuario usuario = this.cenarioUsuario.getUsuario();
        Lancamento lancamentoOriginal = this.cenario.criarLancamento("Lançamento para atualizar", usuario);

        LancamentoAtualizacaoForm form = LancamentoAtualizacaoForm.builder()
                .categoriaId(lancamentoOriginal.getCategoriaId())
                .formaPagamentoId(lancamentoOriginal.getFormaPagamentoId())
                .descricao("")
                .data(lancamentoOriginal.getData())
                .valor(BigDecimal.valueOf(110L))
                .build();

        URI uri = new URI("/lancamentos/" + lancamentoOriginal.getId());
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put(uri)
                                .content(new ObjectMapper().writeValueAsString(form))
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(headers(usuario))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void naoDeveriaAtualizarUmLancamentoSemCategoria() throws Exception {
        Usuario usuario = this.cenarioUsuario.getUsuario();
        Lancamento lancamentoOriginal = this.cenario.criarLancamento("Lançamento para atualizar", usuario);

        LancamentoAtualizacaoForm form = LancamentoAtualizacaoForm.builder()
                .formaPagamentoId(lancamentoOriginal.getFormaPagamentoId())
                .descricao("Nova descrição")
                .data(lancamentoOriginal.getData())
                .valor(BigDecimal.valueOf(110L))
                .build();

        URI uri = new URI("/lancamentos/" + lancamentoOriginal.getId());
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put(uri)
                                .content(new ObjectMapper().writeValueAsString(form))
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(headers(usuario))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void naoDeveriaAtualizarUmLancamentoSemFormaPagamento() throws Exception {
        Usuario usuario = this.cenarioUsuario.getUsuario();
        Lancamento lancamentoOriginal = this.cenario.criarLancamento("Lançamento para atualizar", usuario);

        LancamentoAtualizacaoForm form = LancamentoAtualizacaoForm.builder()
                .categoriaId(lancamentoOriginal.getCategoriaId())
                .descricao("Nova descrição")
                .data(lancamentoOriginal.getData())
                .valor(BigDecimal.valueOf(110L))
                .build();

        URI uri = new URI("/lancamentos/" + lancamentoOriginal.getId());
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put(uri)
                                .content(new ObjectMapper().writeValueAsString(form))
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(headers(usuario))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void naoDeveriaAtualizarUmLancamentoSemValor() throws Exception {
        Usuario usuario = this.cenarioUsuario.getUsuario();
        Lancamento lancamentoOriginal = this.cenario.criarLancamento("Lançamento para atualizar", usuario);

        LancamentoAtualizacaoForm form = LancamentoAtualizacaoForm.builder()
                .categoriaId(lancamentoOriginal.getCategoriaId())
                .formaPagamentoId(lancamentoOriginal.getFormaPagamentoId())
                .descricao("Nova descrição")
                .data(lancamentoOriginal.getData())
                .build();

        URI uri = new URI("/lancamentos/" + lancamentoOriginal.getId());
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put(uri)
                                .content(new ObjectMapper().writeValueAsString(form))
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(headers(usuario))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void naoDeveriaAtualizarUmLancamentoComValorZerado() throws Exception {
        Usuario usuario = this.cenarioUsuario.getUsuario();
        Lancamento lancamentoOriginal = this.cenario.criarLancamento("Lançamento para atualizar", usuario);

        LancamentoAtualizacaoForm form = LancamentoAtualizacaoForm.builder()
                .categoriaId(lancamentoOriginal.getCategoriaId())
                .formaPagamentoId(lancamentoOriginal.getFormaPagamentoId())
                .descricao("Nova descrição")
                .data(lancamentoOriginal.getData())
                .valor(BigDecimal.ZERO)
                .build();

        URI uri = new URI("/lancamentos/" + lancamentoOriginal.getId());
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put(uri)
                                .content(new ObjectMapper().writeValueAsString(form))
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(headers(usuario))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void naoDeveriaAtualizarUmLancamentoComValorNegativo() throws Exception {
        Usuario usuario = this.cenarioUsuario.getUsuario();
        Lancamento lancamentoOriginal = this.cenario.criarLancamento("Lançamento para atualizar", usuario);

        LancamentoAtualizacaoForm form = LancamentoAtualizacaoForm.builder()
                .categoriaId(lancamentoOriginal.getCategoriaId())
                .formaPagamentoId(lancamentoOriginal.getFormaPagamentoId())
                .descricao("Nova descrição")
                .data(lancamentoOriginal.getData())
                .valor(BigDecimal.TEN.negate())
                .build();

        URI uri = new URI("/lancamentos/" + lancamentoOriginal.getId());
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put(uri)
                                .content(new ObjectMapper().writeValueAsString(form))
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(headers(usuario))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void deveriaRemoverLancamento() throws Exception{

        Usuario usuario = this.cenarioUsuario.getUsuario();
        Lancamento dinheiro = this.cenario.criarLancamento("Remover este lançamento", usuario);

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .delete(new URI("/lancamentos/" + dinheiro.getId()))
                                .headers(headers(usuario))
                )
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    private HttpHeaders headers(Usuario usuario) throws Exception {
        return new RequestCenarioTest(mockMvc).headers(usuario);
    }
}
