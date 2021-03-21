package br.eckelp.lancamentoconta.categoria.usecase;

import br.eckelp.lancamentoconta.app.exception.ArgumentoInvalidoException;
import br.eckelp.lancamentoconta.app.exception.ObjetoNaoEncontradoException;
import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.app.security.repository.IUsuarioRepository;
import br.eckelp.lancamentoconta.app.security.service.EncoderService;
import br.eckelp.lancamentoconta.categoria.CategoriaCenarioTest;
import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.dominio.dto.CategoriaAtualizacaoForm;
import br.eckelp.lancamentoconta.categoria.dominio.interfaces.IAtualizarCategoriaUseCase;
import br.eckelp.lancamentoconta.categoria.infra.ICategoriaRepository;
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
public class AtualizarCategoriaUseCaseTest {

    @Autowired
    private ICategoriaRepository repository;
    @Autowired
    private IAtualizarCategoriaUseCase atualizarCategoriaUseCase;
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private EncoderService encoderService;

    private CategoriaCenarioTest cenario;
    private UsuarioCenarioTest cenarioUsuario;

    @Before
    public void construirCenario() {
        this.cenario = new CategoriaCenarioTest(repository);
        this.cenarioUsuario = new UsuarioCenarioTest(encoderService, usuarioRepository);
    }

    @Test
    public void deveAtualizarUmaCategoria(){

        Usuario usuario = this.cenarioUsuario.getUsuario();
        Categoria categoria = this.cenario.criarUmaCategoria("Mercado", usuario);

        Categoria categoriaAtualizada = atualizarCategoriaUseCase.atualizar(categoria.getId(), new CategoriaAtualizacaoForm("Farmácia"));

        Assert.assertEquals("", "Farmácia", categoriaAtualizada.getDescricao());

    }

    @Test(expected = ObjetoNaoEncontradoException.class)
    public void deveLancarExceptionQuandoNaoEncontrarUmaCategoriaParaAtualizar() {
        atualizarCategoriaUseCase.atualizar(-1, new CategoriaAtualizacaoForm("Farmácia"));
    }

    @Test(expected = ArgumentoInvalidoException.class)
    public void deveLancarExceptionQuandoTentarAtualizarCategoriaVazia() {
        Usuario usuario = this.cenarioUsuario.getUsuario();
        Categoria categoria = this.cenario.criarUmaCategoria("Mercado", usuario);

        atualizarCategoriaUseCase.atualizar(categoria.getId(), new CategoriaAtualizacaoForm(""));

    }

}
