package br.eckelp.lancamentoconta.formaPagamento.useCase;

import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.formaPagamento.FormaPagamentoCenarioTest;
import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formaPagamento.dominio.interfaces.IRemoverFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formaPagamento.infra.IFormaPagamentoRepository;
import org.junit.Assert;
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
public class RemoverFormaPagamentoUseCaseTest {

    @Autowired
    private IFormaPagamentoRepository repository;

    @Autowired
    private IRemoverFormaPagamentoUseCase removerFormaPagamentoUseCase;

    @Test
    public void deveRemoverUmaFormaPagamento(){

        FormaPagamento dinheiro = new FormaPagamentoCenarioTest(repository).criarFormaPagamento("Dinheiro");

        removerFormaPagamentoUseCase.remover(dinheiro.getId());

        Optional<FormaPagamento> formaPagamento = repository.findById(dinheiro.getId());

        Assert.assertTrue(formaPagamento.isEmpty());

    }

}
