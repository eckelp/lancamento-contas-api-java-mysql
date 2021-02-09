package br.eckelp.lancamentoconta.formaPagamento.useCase;

import br.eckelp.lancamentoconta.categoria.dominio.dto.CategoriaAtualizacaoForm;
import br.eckelp.lancamentoconta.formaPagamento.FormaPagamentoCenarioTest;
import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formaPagamento.dominio.dto.FormaPagamentoAtualizacaoForm;
import br.eckelp.lancamentoconta.formaPagamento.dominio.interfaces.IAtualizarFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formaPagamento.infra.IFormaPagamentoRepository;
import br.eckelp.lancamentoconta.sistema.infra.exception.ArgumentoInvalidoException;
import br.eckelp.lancamentoconta.sistema.infra.exception.ObjetoNaoEncontradoException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AtualizarFormaPagamentoUseCaseTest {

    @Autowired
    private IFormaPagamentoRepository repository;

    @Autowired
    private IAtualizarFormaPagamentoUseCase atualizarFormaPagamentoUseCase;

    @Test
    public void deveAtualizarUmaFormaPagamento(){

        FormaPagamento dinheiro = new FormaPagamentoCenarioTest(repository).criarFormaPagamento("Dinheiro");

        FormaPagamento formaPagamentoAtualizada = atualizarFormaPagamentoUseCase.atualizar(dinheiro.getId(), new FormaPagamentoAtualizacaoForm("Alimentação"));

        Assert.assertEquals("Alimentação", formaPagamentoAtualizada.getDescricao());

    }

    @Test(expected = ObjetoNaoEncontradoException.class)
    public void deveLancarExceptionQuandoNaoEncontrarUmaFormaPagamentoParaAtualizar() {

        atualizarFormaPagamentoUseCase.atualizar(-1, new FormaPagamentoAtualizacaoForm("Farmácia"));

    }

    @Test(expected = ArgumentoInvalidoException.class)
    public void deveLancarExceptionQuandoTentarAtualizarFormaPagamentoVazia() {
        FormaPagamento dinheiro = new FormaPagamentoCenarioTest(repository).criarFormaPagamento("Dinheiro");

        atualizarFormaPagamentoUseCase.atualizar(dinheiro.getId(), new FormaPagamentoAtualizacaoForm(""));

    }

}
