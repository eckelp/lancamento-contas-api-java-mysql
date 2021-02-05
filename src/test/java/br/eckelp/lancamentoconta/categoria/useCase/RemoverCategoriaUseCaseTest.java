package br.eckelp.lancamentoconta.categoria.useCase;


import br.eckelp.lancamentoconta.categoria.CategoriaCenarioTest;
import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.dominio.interfaces.IRemoverCategoriaUseCase;
import br.eckelp.lancamentoconta.categoria.infra.ICategoriaRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class RemoverCategoriaUseCaseTest {

    @Autowired
    private ICategoriaRepository repository;

    @Autowired
    private IRemoverCategoriaUseCase removerCategoriaUseCase;

    private Integer id;

    @Before
    public void cenario(){
        Categoria categoria = new CategoriaCenarioTest(repository).criarUmaCategoria("Remover");

        this.id = categoria.getId();

    }

    @Test
    public void deveRemoverUmaCategoria(){

        removerCategoriaUseCase.remover(this.id);

        Optional<Categoria> categoria = repository.findById(this.id);

        Assert.assertTrue(categoria.isEmpty());

    }

}