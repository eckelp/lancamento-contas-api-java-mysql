package br.eckelp.lancamentoconta.formaPagamento.infra;

import br.eckelp.lancamentoconta.formaPagamento.FormaPagamentoCenarioTest;
import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
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
public class IFormaPagamentoRepositoryTest {

    @Autowired
    private IFormaPagamentoRepository repository;

    @Before
    public void construirCenario() {
        new FormaPagamentoCenarioTest(repository).criarListaFormasPagamentoValidas();
    }


    @Test
    public void deveriaCarregarListaDeFormasPagamento() {

        List<FormaPagamento> formasPagamento = repository.listarFormasPagamento();

        Assert.assertFalse(formasPagamento.isEmpty());

    }

    @Test
    public void deveriaCarregarUmaFormaPagamento() {
        FormaPagamento formaPagamento = repository.save(new FormaPagamento("Dinheiro"));

        Optional<FormaPagamento> formaPagamentoOptional = repository.findById(formaPagamento.getId());

        Assert.assertTrue(formaPagamentoOptional.isPresent());
    }

    @Test
    public void naoDeveriaCarregarUmaFormaPagamentoInvalida() {
        Optional<FormaPagamento> formaPagamento = repository.findById(-1);

        Assert.assertTrue(formaPagamento.isEmpty());
    }

}
