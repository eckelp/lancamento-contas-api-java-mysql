package br.eckelp.lancamentoconta.formapagamento.controller;

import br.eckelp.lancamentoconta.RequestCenarioTest;
import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.app.security.repository.IUsuarioRepository;
import br.eckelp.lancamentoconta.app.security.service.EncoderService;
import br.eckelp.lancamentoconta.formapagamento.FormaPagamentoCenarioTest;
import br.eckelp.lancamentoconta.formapagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formapagamento.dominio.dto.FormaPagamentoAtualizacaoForm;
import br.eckelp.lancamentoconta.formapagamento.dominio.dto.FormaPagamentoCadastroForm;
import br.eckelp.lancamentoconta.formapagamento.infra.IFormaPagamentoRepository;
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

    @Autowired
    private EncoderService encoderService;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    private FormaPagamentoCenarioTest cenario;
    private UsuarioCenarioTest cenarioUsuario;

    @Before
    public void cenario(){
        this.cenario = new FormaPagamentoCenarioTest(repository);
        this.cenarioUsuario = new UsuarioCenarioTest(encoderService, usuarioRepository);
    }

    @Test
    public void deveCriarUmaFormaPagamento() throws Exception {

        Usuario usuario = this.cenarioUsuario.getUsuario();

        URI uri = new URI("/formas-pagamento");

        FormaPagamentoCadastroForm form = new FormaPagamentoCadastroForm("Dinheiro");

        mockMvc
            .perform(
                    MockMvcRequestBuilders
                        .post(uri)
                            .headers(headers(usuario))
                            .content(new ObjectMapper().writeValueAsString(form))
                        .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.descricao").value(form.getDescricao()));

    }

    @Test
    public void naoDeveriaCriarFormaPagamentoComDescricaoVazia() throws Exception{
        Usuario usuario = this.cenarioUsuario.getUsuario();

        URI uri = new URI("/formas-pagamento");
        FormaPagamentoCadastroForm form = new FormaPagamentoCadastroForm("");

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post(uri)
                                .headers(headers(usuario))
                                .content(new ObjectMapper().writeValueAsString(form))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void deveriaRemoverFormaPagamento() throws Exception{
        Usuario usuario = this.cenarioUsuario.getUsuario();

        FormaPagamento dinheiro = this.cenario.criarFormaPagamento("Dinheiro", usuario);
        repository.save(dinheiro);

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .delete(new URI("/formas-pagamento/" + dinheiro.getId()))
                                .headers(headers(usuario))
                )
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void deveRetornar200AoTentarRemoverFormaPagamentoQueNaoExiste() throws Exception{
        Usuario usuario = this.cenarioUsuario.getUsuario();

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .delete(new URI("/formas-pagamento/999"))
                                .headers(headers(usuario))
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveriaAtualizarUmaFormaPagamento() throws Exception {
        Usuario usuario = this.cenarioUsuario.getUsuario();

        FormaPagamento dinheiro = this.cenario.criarFormaPagamento("Dinheiro", usuario);

        repository.save(dinheiro);

        FormaPagamentoAtualizacaoForm form = new FormaPagamentoAtualizacaoForm("Crédito");
        URI uri = new URI("/formas-pagamento/" + dinheiro.getId());
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put(uri)
                                .headers(headers(usuario))
                                .content(new ObjectMapper().writeValueAsString(form))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.descricao").value(form.getDescricao()));

    }

    @Test
    public void naoDeveriaAtualizarFormaPagamentoComDescricaoVazia() throws Exception{
        Usuario usuario = this.cenarioUsuario.getUsuario();

        FormaPagamento dinheiro = this.cenario.criarFormaPagamento("Dinheiro", usuario);
        repository.save(dinheiro);

        FormaPagamentoAtualizacaoForm form = new FormaPagamentoAtualizacaoForm("");
        URI uri = new URI("/formas-pagamento/" + dinheiro.getId());
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put(uri)
                                .headers(headers(usuario))
                                .content(new ObjectMapper().writeValueAsString(form))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void deveriarListarAsCategorias() throws Exception {
        Usuario usuario = this.cenarioUsuario.getUsuario();

        List<FormaPagamento> formasPagamento = repository.saveAll(
            Arrays.asList(
                new FormaPagamento("Dinheiro", usuario),
                new FormaPagamento("Crédito", usuario)
            )
        );

        URI uri = new URI("/formas-pagamento");

        mockMvc
                .perform(
                        MockMvcRequestBuilders.get(uri)
                        .headers(headers(usuario))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(formasPagamento)));
    }

    public HttpHeaders headers(Usuario usuario) throws Exception {
        return new RequestCenarioTest(mockMvc).headers(usuario);
    }


}
