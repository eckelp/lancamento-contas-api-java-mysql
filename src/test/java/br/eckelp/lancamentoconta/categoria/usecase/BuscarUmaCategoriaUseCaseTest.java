package br.eckelp.lancamentoconta.categoria.usecase;

import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.app.security.repository.IUsuarioRepository;
import br.eckelp.lancamentoconta.app.security.service.EncoderService;
import br.eckelp.lancamentoconta.categoria.CategoriaCenarioTest;
import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.dominio.interfaces.IBuscarUmaCategoriaUseCase;
import br.eckelp.lancamentoconta.categoria.infra.ICategoriaRepository;
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
public class BuscarUmaCategoriaUseCaseTest {

    @Autowired
    private ICategoriaRepository repository;

    @Autowired
    private IBuscarUmaCategoriaUseCase buscarUmaCategoriaUseCase;
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private EncoderService encoderService;

    private CategoriaCenarioTest cenario;
    private UsuarioCenarioTest cenarioUsuario;

    @Before
    public void cenario() {
        this.cenario = new CategoriaCenarioTest(repository);
        this.cenarioUsuario = new UsuarioCenarioTest(encoderService, usuarioRepository);
    }

    @Test
    public void deveRetornarUmaCategoria(){

        Usuario usuario = this.cenarioUsuario.getUsuario();
        Categoria categoria = this.cenario.criarUmaCategoria("Mercado", usuario);

        Categoria categoriaEncontrada = buscarUmaCategoriaUseCase.porId(categoria.getId());

        Assert.assertNotNull(categoriaEncontrada);

    }

    @Test(expected = ObjetoNaoEncontradoException.class)
    public void deveLancarExceptionQuandoNaoEncontrarCategoriaPeloId() {

        buscarUmaCategoriaUseCase.porId(-1);

    }

}
