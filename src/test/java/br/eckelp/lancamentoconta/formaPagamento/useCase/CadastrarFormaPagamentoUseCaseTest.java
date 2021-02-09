package br.eckelp.lancamentoconta.formaPagamento.useCase;

import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.dominio.dto.CategoriaCadastroForm;
import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formaPagamento.dominio.dto.FormaPagamentoCadastroForm;
import br.eckelp.lancamentoconta.formaPagamento.dominio.interfaces.ICadastrarFormaPagamentoUseCase;
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
public class CadastrarFormaPagamentoUseCaseTest {

    @Autowired
    private ICadastrarFormaPagamentoUseCase cadastrarFormaPagamentoUseCase;

    @Test
    public void deveCadastrarUmaFormaPagamento(){

        FormaPagamento dinheiro = cadastrarFormaPagamentoUseCase.cadastrar(new FormaPagamentoCadastroForm("Dinheiro"));

        Assert.assertNotNull(dinheiro);
    }

    @Test(expected = ArgumentoInvalidoException.class)
    public void deveLancarExcecaoAoCadastrarCategoriaVazia(){
        cadastrarFormaPagamentoUseCase.cadastrar(new FormaPagamentoCadastroForm());
    }


}
