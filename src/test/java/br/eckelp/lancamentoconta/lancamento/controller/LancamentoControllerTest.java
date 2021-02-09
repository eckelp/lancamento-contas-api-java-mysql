package br.eckelp.lancamentoconta.lancamento.controller;

import br.eckelp.lancamentoconta.categoria.CategoriaCenarioTest;
import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.infra.ICategoriaRepository;
import br.eckelp.lancamentoconta.formaPagamento.FormaPagamentoCenarioTest;
import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formaPagamento.infra.IFormaPagamentoRepository;
import br.eckelp.lancamentoconta.lancamento.LancamentoCenarioTest;
import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;
import br.eckelp.lancamentoconta.lancamento.dominio.dto.LancamentoAtualizacaoForm;
import br.eckelp.lancamentoconta.lancamento.dominio.dto.LancamentoCadastroForm;
import br.eckelp.lancamentoconta.lancamento.infra.ILancamentoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Test
    public void deveCriarUmLancamento() throws Exception {
        new LancamentoCenarioTest(repository).inicializarCenarioVazio();
        Categoria mercado = new CategoriaCenarioTest(categoriaRepository).criarUmaCategoria("Mercado");
        FormaPagamento dinheiro = new FormaPagamentoCenarioTest(formaPagamentoRepository).criarFormaPagamento("Dinheiro");
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
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.descricao").value(form.getDescricao()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.valor").value(form.getValor()));
    }

    @Test
    public void naoDeveriaCriarLancamentoComDescricaoVazia() throws Exception{
        new LancamentoCenarioTest(repository).inicializarCenarioVazio();
        Categoria mercado = new CategoriaCenarioTest(categoriaRepository).criarUmaCategoria("Mercado");
        FormaPagamento dinheiro = new FormaPagamentoCenarioTest(formaPagamentoRepository).criarFormaPagamento("Dinheiro");

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
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void naoDeveriaCriarLancamentoSemCategoria() throws Exception{
        new LancamentoCenarioTest(repository).inicializarCenarioVazio();
        FormaPagamento dinheiro = new FormaPagamentoCenarioTest(formaPagamentoRepository).criarFormaPagamento("Dinheiro");

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
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void naoDeveriaCriarLancamentoSemFormaPagamento() throws Exception{
        new LancamentoCenarioTest(repository).inicializarCenarioVazio();
        Categoria mercado = new CategoriaCenarioTest(categoriaRepository).criarUmaCategoria("Mercado");

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
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void naoDeveriaCriarLancamentoSemValor() throws Exception{
        new LancamentoCenarioTest(repository).inicializarCenarioVazio();
        Categoria mercado = new CategoriaCenarioTest(categoriaRepository).criarUmaCategoria("Mercado");
        FormaPagamento dinheiro = new FormaPagamentoCenarioTest(formaPagamentoRepository).criarFormaPagamento("Dinheiro");

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
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void naoDeveriaCriarLancamentoComValorZerado() throws Exception{
        new LancamentoCenarioTest(repository).inicializarCenarioVazio();
        Categoria mercado = new CategoriaCenarioTest(categoriaRepository).criarUmaCategoria("Mercado");
        FormaPagamento dinheiro = new FormaPagamentoCenarioTest(formaPagamentoRepository).criarFormaPagamento("Dinheiro");

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
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void naoDeveriaCriarLancamentoComValorNegativo() throws Exception{
        new LancamentoCenarioTest(repository).inicializarCenarioVazio();
        Categoria mercado = new CategoriaCenarioTest(categoriaRepository).criarUmaCategoria("Mercado");
        FormaPagamento dinheiro = new FormaPagamentoCenarioTest(formaPagamentoRepository).criarFormaPagamento("Dinheiro");

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
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void deveriaAtualizarUmLancamento() throws Exception {
        Lancamento lancamentoOriginal = new LancamentoCenarioTest(repository, categoriaRepository, formaPagamentoRepository).criarLancamento("Lançamento para atualizar");

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
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.descricao").value(form.getDescricao()));

    }

    @Test
    public void naoDeveriaAtualizarUmLancamentoSemDescricao() throws Exception {
        Lancamento lancamentoOriginal = new LancamentoCenarioTest(repository, categoriaRepository, formaPagamentoRepository).criarLancamento("Lançamento para atualizar");

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
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void naoDeveriaAtualizarUmLancamentoSemCategoria() throws Exception {
        Lancamento lancamentoOriginal = new LancamentoCenarioTest(repository, categoriaRepository, formaPagamentoRepository).criarLancamento("Lançamento para atualizar");

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
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void naoDeveriaAtualizarUmLancamentoSemFormaPagamento() throws Exception {
        Lancamento lancamentoOriginal = new LancamentoCenarioTest(repository, categoriaRepository, formaPagamentoRepository).criarLancamento("Lançamento para atualizar");

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
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void naoDeveriaAtualizarUmLancamentoSemValor() throws Exception {
        Lancamento lancamentoOriginal = new LancamentoCenarioTest(repository, categoriaRepository, formaPagamentoRepository).criarLancamento("Lançamento para atualizar");

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
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void naoDeveriaAtualizarUmLancamentoComValorZerado() throws Exception {
        Lancamento lancamentoOriginal = new LancamentoCenarioTest(repository, categoriaRepository, formaPagamentoRepository).criarLancamento("Lançamento para atualizar");

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
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void naoDeveriaAtualizarUmLancamentoComValorNegativo() throws Exception {
        Lancamento lancamentoOriginal = new LancamentoCenarioTest(repository, categoriaRepository, formaPagamentoRepository).criarLancamento("Lançamento para atualizar");

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
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void deveriaRemoverLancamento() throws Exception{

        Lancamento dinheiro = new LancamentoCenarioTest(repository, categoriaRepository, formaPagamentoRepository).criarLancamento("Remover este lançamento");

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .delete(new URI("/lancamentos/" + dinheiro.getId()))
                )
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}
