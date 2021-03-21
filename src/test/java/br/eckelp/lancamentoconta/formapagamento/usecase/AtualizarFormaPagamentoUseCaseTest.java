package br.eckelp.lancamentoconta.formapagamento.usecase;

import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.app.security.repository.IUsuarioRepository;
import br.eckelp.lancamentoconta.app.security.service.EncoderService;
import br.eckelp.lancamentoconta.formapagamento.FormaPagamentoCenarioTest;
import br.eckelp.lancamentoconta.formapagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formapagamento.dominio.dto.FormaPagamentoAtualizacaoForm;
import br.eckelp.lancamentoconta.formapagamento.dominio.interfaces.IAtualizarFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formapagamento.dominio.interfaces.IBuscarUmaFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formapagamento.infra.IFormaPagamentoRepository;
import br.eckelp.lancamentoconta.app.exception.ArgumentoInvalidoException;
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
public class AtualizarFormaPagamentoUseCaseTest {

    @Autowired
    private IFormaPagamentoRepository repository;
    @Autowired
    private IAtualizarFormaPagamentoUseCase atualizarFormaPagamentoUseCase;
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
    public void deveAtualizarUmaFormaPagamento(){
        Usuario usuario = this.cenarioUsuario.getUsuario();
        FormaPagamento dinheiro = this.cenario.criarFormaPagamento("Dinheiro", usuario);

        FormaPagamento formaPagamentoAtualizada = atualizarFormaPagamentoUseCase.atualizar(dinheiro.getId(), new FormaPagamentoAtualizacaoForm("Alimentação"));

        Assert.assertEquals("Alimentação", formaPagamentoAtualizada.getDescricao());

    }

    @Test(expected = ObjetoNaoEncontradoException.class)
    public void deveLancarExceptionQuandoNaoEncontrarUmaFormaPagamentoParaAtualizar() {

        atualizarFormaPagamentoUseCase.atualizar(-1, new FormaPagamentoAtualizacaoForm("Farmácia"));

    }

    @Test(expected = ArgumentoInvalidoException.class)
    public void deveLancarExceptionQuandoTentarAtualizarFormaPagamentoVazia() {
        Usuario usuario = this.cenarioUsuario.getUsuario();
        FormaPagamento dinheiro = this.cenario.criarFormaPagamento("Dinheiro", usuario);

        atualizarFormaPagamentoUseCase.atualizar(dinheiro.getId(), new FormaPagamentoAtualizacaoForm(""));

    }

}
