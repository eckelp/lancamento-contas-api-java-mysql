package br.eckelp.lancamentoconta.categoria.useCase;

import br.eckelp.lancamentoconta.categoria.CategoriaCenarioTest;
import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.dominio.dto.CategoriaAtualizacaoForm;
import br.eckelp.lancamentoconta.categoria.dominio.interfaces.IAtualizarCategoriaUseCase;
import br.eckelp.lancamentoconta.categoria.infra.ICategoriaRepository;
import br.eckelp.lancamentoconta.sistema.infra.exception.ArgumentoInvalidoException;
import br.eckelp.lancamentoconta.sistema.infra.exception.ObjetoNaoEncontradoException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AtualizarCategoriaUseCaseTest {

    @Autowired
    private ICategoriaRepository repository;

    @Autowired
    private IAtualizarCategoriaUseCase atualizarCategoriaUseCase;

    private Integer id = null;

    @Before
    public void cenario(){

        Categoria categoria = new CategoriaCenarioTest(repository).criarUmaCategoria("Mercado");

        this.id = categoria.getId();

    }

    @Test
    public void deveAtualizarUmaCategoria(){

        Categoria categoriaAtualizada = atualizarCategoriaUseCase.atualizar(id, new CategoriaAtualizacaoForm("Farmácia"));

        Assert.assertEquals("", "Farmácia", categoriaAtualizada.getDescricao());

    }

    @Test(expected = ObjetoNaoEncontradoException.class)
    public void deveLancarExceptionQuandoNaoEncontrarUmaCategoriaParaAtualizar() {

        atualizarCategoriaUseCase.atualizar(-1, new CategoriaAtualizacaoForm("Farmácia"));

    }

    @Test(expected = ArgumentoInvalidoException.class)
    public void deveLancarExceptionQuandoTentarAtualizarCategoriaVazia() {


        atualizarCategoriaUseCase.atualizar(id, new CategoriaAtualizacaoForm(""));

    }

}
