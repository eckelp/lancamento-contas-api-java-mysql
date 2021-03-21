package br.eckelp.lancamentoconta.formapagamento.usecase;

import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.app.security.repository.IUsuarioRepository;
import br.eckelp.lancamentoconta.app.security.service.EncoderService;
import br.eckelp.lancamentoconta.formapagamento.FormaPagamentoCenarioTest;
import br.eckelp.lancamentoconta.formapagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formapagamento.dominio.interfaces.IBuscarUmaFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formapagamento.infra.IFormaPagamentoRepository;
import br.eckelp.lancamentoconta.app.exception.ObjetoNaoEncontradoException;
import br.eckelp.lancamentoconta.usuario.UsuarioCenarioTest;
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
public class BuscarUmaFormaPagamentoUseCaseTest {

    @Autowired
    private IFormaPagamentoRepository repository;
    @Autowired
    private IBuscarUmaFormaPagamentoUseCase buscarUmaFormaPagamentoUseCase;
    @Autowired
    private EncoderService encoderService;
    @Autowired
    private IUsuarioRepository usuarioRepository;

    private FormaPagamentoCenarioTest cenario;
    private UsuarioCenarioTest cenarioUsuario;

    @Before
    public void cenario(){
        this.cenario = new FormaPagamentoCenarioTest(repository);
        this.cenarioUsuario = new UsuarioCenarioTest(encoderService, usuarioRepository);
    }

    @Test
    public void deveRetornarUmaFormaPagamentoPeloId() {
        Usuario usuario = this.cenarioUsuario.getUsuario();
        FormaPagamento dinheiro = this.cenario.criarFormaPagamento("Dinheiro", usuario);

        FormaPagamento formaPagamentoEncontrada = buscarUmaFormaPagamentoUseCase.porId(dinheiro.getId());

        Assert.assertNotNull(formaPagamentoEncontrada);

    }

    @Test(expected = ObjetoNaoEncontradoException.class)
    public void deveLancarExceptionQuandoNaoEncontrarFormaPagamentoPeloId() {

        buscarUmaFormaPagamentoUseCase.porId(-1);

    }

}
