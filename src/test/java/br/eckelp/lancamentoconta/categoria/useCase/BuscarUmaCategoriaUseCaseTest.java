package br.eckelp.lancamentoconta.categoria.useCase;

import br.eckelp.lancamentoconta.categoria.CategoriaCenarioTest;
import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.dominio.interfaces.IBuscarUmaCategoriaUseCase;
import br.eckelp.lancamentoconta.categoria.infra.ICategoriaRepository;
import org.junit.Assert;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BuscarUmaCategoriaUseCaseTest {

    @Autowired
    private IBuscarUmaCategoriaUseCase buscarUmaCategoriaUseCase;

    @Autowired
    private ICategoriaRepository repository;

    @Before
    public void cenario() {
        new CategoriaCenarioTest(repository).criarUmaCategoria("Mercado");
    }

    @Test
    public void deveRetornarUmaCategoria(){

        Categoria categoria = buscarUmaCategoriaUseCase.porId(1);

        Assert.assertNotNull(categoria);

    }

}
