package br.eckelp.lancamentoconta.formaPagamento.useCase;

import br.eckelp.lancamentoconta.formaPagamento.FormaPagamentoCenarioTest;
import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formaPagamento.dominio.interfaces.IBuscarTodasFormasPagamentoUseCase;
import br.eckelp.lancamentoconta.formaPagamento.infra.IFormaPagamentoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class BuscarTodasFormasPagamentoUseCaseTest {

    @Autowired
    private IFormaPagamentoRepository repository;

    @Autowired
    private IBuscarTodasFormasPagamentoUseCase buscarTodasFormasPagamentoUseCase;

    @Test
    public void deveRetornarListaDeFormasPagamento() {
        new FormaPagamentoCenarioTest(repository).criarListaFormasPagamentoValidas();

        List<FormaPagamento> formasPagamento = buscarTodasFormasPagamentoUseCase.buscarTodas();

        Assert.assertFalse(formasPagamento.isEmpty());

    }

}
