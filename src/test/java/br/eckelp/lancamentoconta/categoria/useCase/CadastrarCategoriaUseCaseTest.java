package br.eckelp.lancamentoconta.categoria.useCase;

import br.eckelp.lancamentoconta.categoria.CategoriaCenarioTest;
import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.dominio.dto.CategoriaCadastroForm;
import br.eckelp.lancamentoconta.categoria.dominio.interfaces.ICadastrarCategoriaUseCase;
import br.eckelp.lancamentoconta.sistema.infra.exception.ArgumentoInvalidoException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CadastrarCategoriaUseCaseTest {

    @Autowired
    private ICadastrarCategoriaUseCase cadastrarCategoriaUseCase;

    @Test
    public void deveCadastrarUmaCategoria(){

        Categoria categoria = cadastrarCategoriaUseCase.cadastrar(new CategoriaCadastroForm("Mercado"));

        Assert.assertNotNull(categoria);
    }

    @Test(expected = ArgumentoInvalidoException.class)
    public void deveLancarExcecaoAoCadastrarCategoriaVazia(){
        cadastrarCategoriaUseCase.cadastrar(new CategoriaCadastroForm());
    }


}
