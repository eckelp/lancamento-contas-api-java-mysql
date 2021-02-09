package br.eckelp.lancamentoconta.formaPagamento.useCase;

import br.eckelp.lancamentoconta.formaPagamento.FormaPagamentoCenarioTest;
import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formaPagamento.dominio.interfaces.IBuscarUmaFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formaPagamento.infra.IFormaPagamentoRepository;
import br.eckelp.lancamentoconta.sistema.infra.exception.ObjetoNaoEncontradoException;
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
public class BuscarUmaFormaPagamentoUseCaseTest {

    @Autowired
    private IFormaPagamentoRepository repository;

    @Autowired
    private IBuscarUmaFormaPagamentoUseCase buscarUmaFormaPagamentoUseCase;

    @Test
    public void deveRetornarUmaFormaPagamentoPeloId() {
        FormaPagamento dinheiro = new FormaPagamentoCenarioTest(repository).criarFormaPagamento("Dinheiro");

        FormaPagamento formaPagamentoEncontrada = buscarUmaFormaPagamentoUseCase.porId(dinheiro.getId());

        Assert.assertNotNull(formaPagamentoEncontrada);

    }

    @Test(expected = ObjetoNaoEncontradoException.class)
    public void deveLancarExceptionQuandoNaoEncontrarFormaPagamentoPeloId() {

        buscarUmaFormaPagamentoUseCase.porId(-1);

    }

}
