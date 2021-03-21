package br.eckelp.lancamentoconta.formapagamento.infra;

import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.app.security.repository.IUsuarioRepository;
import br.eckelp.lancamentoconta.app.security.service.EncoderService;
import br.eckelp.lancamentoconta.formapagamento.FormaPagamentoCenarioTest;
import br.eckelp.lancamentoconta.formapagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.usuario.UsuarioCenarioTest;
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
    @Autowired
    private EncoderService encoderService;
    @Autowired
    private IUsuarioRepository usuarioRepository;

    private FormaPagamentoCenarioTest cenario;
    private UsuarioCenarioTest cenarioUsuario;

    @Before
    public void construirCenario() {
        this.cenario = new FormaPagamentoCenarioTest(repository);
        this.cenarioUsuario = new UsuarioCenarioTest(encoderService, usuarioRepository);

    }


    @Test
    public void deveriaCarregarListaDeFormasPagamento() {
        Usuario usuario = this.cenarioUsuario.getUsuario();
        this.cenario.criarListaFormasPagamentoValidas(usuario);

        List<FormaPagamento> formasPagamento = repository.listarFormasPagamento(usuario);

        Assert.assertFalse(formasPagamento.isEmpty());

    }

    @Test
    public void deveriaCarregarUmaFormaPagamento() {
        Usuario usuario = this.cenarioUsuario.getUsuario();

        FormaPagamento formaPagamento = this.cenario.criarFormaPagamento("Dinheiro", usuario);

        Optional<FormaPagamento> formaPagamentoOptional = repository.findById(formaPagamento.getId());

        Assert.assertTrue(formaPagamentoOptional.isPresent());
    }

    @Test
    public void naoDeveriaCarregarUmaFormaPagamentoInvalida() {
        Optional<FormaPagamento> formaPagamento = repository.findById(-1);

        Assert.assertTrue(formaPagamento.isEmpty());
    }

}
