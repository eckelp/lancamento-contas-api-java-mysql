package br.eckelp.lancamentoconta.formaPagamento.controller;

import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formaPagamento.dominio.dto.FormaPagamentoAtualizacaoForm;
import br.eckelp.lancamentoconta.formaPagamento.dominio.dto.FormaPagamentoCadastroForm;
import br.eckelp.lancamentoconta.formaPagamento.infra.IFormaPagamentoRepository;
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

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class FormaPagamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IFormaPagamentoRepository repository;

    @Test
    public void deveCriarUmaFormaPagamento() throws Exception {

        URI uri = new URI("/formas-pagamento");

        FormaPagamentoCadastroForm form = new FormaPagamentoCadastroForm("Dinheiro");

        mockMvc
            .perform(
                    MockMvcRequestBuilders
                        .post(uri)
                        .content(new ObjectMapper().writeValueAsString(form))
                        .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.descricao").value(form.getDescricao()));

    }

    @Test
    public void naoDeveriaCriarFormaPagamentoComDescricaoVazia() throws Exception{
        URI uri = new URI("/formas-pagamento");

        FormaPagamentoCadastroForm form = new FormaPagamentoCadastroForm("");

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
    public void deveriaRemoverFormaPagamento() throws Exception{

        FormaPagamento dinheiro = new FormaPagamento("Dinheiro");
        repository.save(dinheiro);

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .delete(new URI("/formas-pagamento/" + dinheiro.getId()))
                )
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void deveRetornar200AoTentarRemoverFormaPagamentoQueNaoExiste() throws Exception{
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .delete(new URI("/formas-pagamento/999"))
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveriaAtualizarUmaFormaPagamento() throws Exception {
        FormaPagamento dinheiro = new FormaPagamento("Dinheiro");
        repository.save(dinheiro);

        FormaPagamentoAtualizacaoForm form = new FormaPagamentoAtualizacaoForm("Crédito");
        URI uri = new URI("/formas-pagamento/" + dinheiro.getId());
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
    public void naoDeveriaAtualizarFormaPagamentoComDescricaoVazia() throws Exception{
        FormaPagamento dinheiro = new FormaPagamento("Dinheiro");
        repository.save(dinheiro);

        FormaPagamentoAtualizacaoForm form = new FormaPagamentoAtualizacaoForm("");
        URI uri = new URI("/formas-pagamento/" + dinheiro.getId());
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
    public void deveriarListarAsCategorias() throws Exception {
        List<FormaPagamento> formasPagamento = repository.saveAll(
            Arrays.asList(
                new FormaPagamento("Dinheiro"),
                new FormaPagamento("Crédito")
            )
        );

        URI uri = new URI("/formas-pagamento");

        mockMvc
                .perform(
                        MockMvcRequestBuilders.get(uri)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(formasPagamento)));
    }


}
