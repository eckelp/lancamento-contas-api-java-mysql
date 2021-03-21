package br.eckelp.lancamentoconta.categoria.usecase.validacao;

import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.app.security.repository.IUsuarioRepository;
import br.eckelp.lancamentoconta.app.security.service.EncoderService;
import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.dominio.interfaces.IValidacaoCategoriaUseCase;
import br.eckelp.lancamentoconta.app.exception.ArgumentoInvalidoException;
import br.eckelp.lancamentoconta.usuario.UsuarioCenarioTest;
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
public class ValidacaoCategoriaDescricaoPreenchidaUseCaseTest {

    @Autowired
    private IValidacaoCategoriaUseCase validacao;
    @Autowired
    private EncoderService encoderService;
    @Autowired
    private IUsuarioRepository usuarioRepository;

    private UsuarioCenarioTest cenarioUsuario;

    @Before
    public void cenario(){
        this.cenarioUsuario = new UsuarioCenarioTest(encoderService, usuarioRepository);
    }

    @Test(expected = ArgumentoInvalidoException.class)
    public void deveLancarExcecaoAoCadastrarCategoriaSemDescricao(){

        Categoria categoriaVazia = new Categoria();

        this.validacao.validar(categoriaVazia);

    }

    @Test
    public void naoDeveLancarExcecaoAoCadastrarCategoriaComDescricao(){

        Usuario usuario = this.cenarioUsuario.getUsuario();

        Categoria mercado = new Categoria("Mercado", usuario);

        this.validacao.validar(mercado);
    }


}
