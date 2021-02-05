package br.eckelp.lancamentoconta.categoria.controller;

import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.dominio.dto.CategoriaAtualizacaoForm;
import br.eckelp.lancamentoconta.categoria.dominio.dto.CategoriaCadastroForm;
import br.eckelp.lancamentoconta.categoria.infra.ICategoriaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
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
//@WebMvcTest //para testar apenas o controller
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ICategoriaRepository repository;

    @Test
    public void deveriaCriarCategoria() throws Exception {

        URI uri = new URI("/categorias");

        CategoriaCadastroForm form = new CategoriaCadastroForm("Transporte");

        String json = new ObjectMapper().writeValueAsString(form);

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post(uri)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.descricao").value(form.getDescricao()));

    }

    @Test
    public void naoDeveriaCriarCategoriaComDescricaoVazia() throws Exception {

        URI uri = new URI("/categorias");

        Categoria categoriaVazia = this.criarCategoriaTemporaria(null);

        String json = new ObjectMapper().writeValueAsString(categoriaVazia);

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post(uri)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());


    }

    @Test
    public void deveriaRemoverUmaCategoria() throws Exception {
        Categoria mercado = this.criarCategoriaTemporaria("Mercado");

        mercado = this.repository.save(mercado);

        URI uri = new URI("/categorias/" + mercado.getId());

        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .delete(uri)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void deveriaAtualizarUmaCategoria() throws Exception {
        Categoria mercado = criarCategoriaTemporaria("Mercado");

        CategoriaAtualizacaoForm form = new CategoriaAtualizacaoForm("Alimentação");
        URI uri = new URI("/categorias/" + mercado.getId());
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
    public void deveriarListarAsCategorias() throws Exception {
        List<Categoria> categorias = Arrays.asList(
                this.criarCategoriaTemporaria("Mercado"),
                this.criarCategoriaTemporaria("Alimentação"),
                this.criarCategoriaTemporaria("Transporte")
        );

        URI uri = new URI("/categorias");

        mockMvc
                .perform(
                        MockMvcRequestBuilders.get(uri)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(categorias)));
    }

    private Categoria criarCategoriaTemporaria(String descricao) {
        Categoria mercado = new Categoria(descricao);
        return this.repository.save(mercado);
    }

}
