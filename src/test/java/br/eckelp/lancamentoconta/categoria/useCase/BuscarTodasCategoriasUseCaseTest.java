package br.eckelp.lancamentoconta.categoria.useCase;

import br.eckelp.lancamentoconta.categoria.CategoriaCenarioTest;
import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.dominio.interfaces.IBuscarTodasCategoriasUseCase;
import br.eckelp.lancamentoconta.categoria.infra.ICategoriaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class BuscarTodasCategoriasUseCaseTest {

    @Autowired
    private ICategoriaRepository repository;

    @Autowired
    private IBuscarTodasCategoriasUseCase buscarTodasCategoriasUseCase;


    @Before
    public void cenario(){
        new CategoriaCenarioTest(repository).criarListaCategoriasValidas();
    }

    @Test
    public void deveRetornarListaDeCategorias(){
        List<Categoria> categorias = buscarTodasCategoriasUseCase.buscarTodas();

        Assert.notEmpty(categorias, "Nenhuma categoria foi encontrada");
    }
}
