package br.eckelp.lancamentoconta.categoria.controller;

import br.eckelp.lancamentoconta.RequestCenarioTest;
import br.eckelp.lancamentoconta.app.security.controller.form.UsuarioForm;
import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.app.security.repository.IUsuarioRepository;
import br.eckelp.lancamentoconta.app.security.service.EncoderService;
import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.dominio.dto.CategoriaAtualizacaoForm;
import br.eckelp.lancamentoconta.categoria.dominio.dto.CategoriaCadastroForm;
import br.eckelp.lancamentoconta.categoria.infra.ICategoriaRepository;
import br.eckelp.lancamentoconta.usuario.UsuarioCenarioTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
//@WebMvcTest //para testar apenas o controller
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ICategoriaRepository repository;

    @Autowired
    private EncoderService encoderService;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    private UsuarioCenarioTest cenarioUsuario;

    @Before
    public void cenario(){
        this.cenarioUsuario = new UsuarioCenarioTest(encoderService, usuarioRepository);
    }

    @Test
    public void deveriaCriarCategoria() throws Exception {
        Usuario usuario = this.cenarioUsuario.getUsuario();

        URI uri = new URI("/categorias");
        CategoriaCadastroForm form = new CategoriaCadastroForm("Transporte");

        String json = new ObjectMapper().writeValueAsString(form);

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post(uri)
                                .headers(headers(usuario))
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.descricao").value(form.getDescricao()));

    }

    @Test
    public void naoDeveriaCriarCategoriaComDescricaoVazia() throws Exception {

        Usuario usuario = this.cenarioUsuario.getUsuario();
        Categoria categoriaVazia = this.criarCategoriaTemporaria(null, usuario);

        URI uri = new URI("/categorias");
        String json = new ObjectMapper().writeValueAsString(categoriaVazia);

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post(uri)
                                .headers(headers(usuario))
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());


    }

    @Test
    public void deveriaRemoverUmaCategoria() throws Exception {
        Usuario usuario = this.cenarioUsuario.getUsuario();

        Categoria mercado = this.criarCategoriaTemporaria("Mercado", usuario);

        mercado = this.repository.save(mercado);

        URI uri = new URI("/categorias/" + mercado.getId());

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .delete(uri)
                                .headers(headers(usuario))
                )
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void deveriaAtualizarUmaCategoria() throws Exception {
        Usuario usuario = this.cenarioUsuario.getUsuario();
        Categoria mercado = criarCategoriaTemporaria("Mercado", usuario);

        CategoriaAtualizacaoForm form = new CategoriaAtualizacaoForm("Alimentação");
        URI uri = new URI("/categorias/" + mercado.getId());
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
    public void deveriarListarAsCategorias() throws Exception {
        Usuario usuario = this.cenarioUsuario.getUsuario();

        List<Categoria> categorias = Arrays.asList(
                this.criarCategoriaTemporaria("Mercado", usuario),
                this.criarCategoriaTemporaria("Alimentação", usuario),
                this.criarCategoriaTemporaria("Transporte", usuario)
        );

        URI uri = new URI("/categorias");

        mockMvc
                .perform(
                        MockMvcRequestBuilders.get(uri)
                                .headers(headers(usuario))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(categorias)));
    }

    private Categoria criarCategoriaTemporaria(String descricao, Usuario usuario) {
        Categoria mercado = new Categoria(descricao, usuario);
        return this.repository.save(mercado);
    }

    public HttpHeaders headers(Usuario usuario) throws Exception {
        return new RequestCenarioTest(mockMvc).headers(usuario);
    }

}
