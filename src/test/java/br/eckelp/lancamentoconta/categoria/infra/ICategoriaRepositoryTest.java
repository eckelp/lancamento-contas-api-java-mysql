package br.eckelp.lancamentoconta.categoria.infra;

import br.eckelp.lancamentoconta.categoria.CategoriaCenarioTest;
import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ICategoriaRepositoryTest {

    @Autowired
    private ICategoriaRepository repository;

    @Before
    public void construirCenario() {

        new CategoriaCenarioTest(repository).criarListaCategoriasValidas();

    }


    @Test
    public void deveriaCarregarListaDeCategorias() {

        List<Categoria> lista = repository.listarCategorias();

        Assert.assertFalse(lista.isEmpty());

    }

    @Test
    public void deveriaCarregarUmaCategoria() {
        List<Categoria> categorias = repository.findAll();

        Assert.assertFalse(categorias.isEmpty());
    }

    @Test
    public void naoDeveriaCarregarUmaCategoria() {
        Optional<Categoria> categoria = repository.findById(-1);

        Assert.assertTrue(categoria.isEmpty());
    }

}

