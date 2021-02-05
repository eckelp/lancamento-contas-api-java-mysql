package br.eckelp.lancamentoconta.categoria.useCase.validacao;

import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.dominio.interfaces.IValidacaoCategoriaUseCase;
import br.eckelp.lancamentoconta.sistema.infra.exception.ArgumentoInvalidoException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ValidacaoCategoriaDescricaoPreenchidaUseCaseTest {

    @Autowired
    private IValidacaoCategoriaUseCase validacao;

    @Test(expected = ArgumentoInvalidoException.class)
    public void deveLancarExcecaoAoCadastrarCategoriaSemDescricao(){

        Categoria categoriaVazia = new Categoria();

        this.validacao.validar(categoriaVazia);

    }

    @Test
    public void naoDeveLancarExcecaoAoCadastrarCategoriaComDescricao(){

        Categoria mercado = new Categoria("Mercado");

        this.validacao.validar(mercado);
    }


}
