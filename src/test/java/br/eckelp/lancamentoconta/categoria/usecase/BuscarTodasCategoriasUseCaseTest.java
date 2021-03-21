package br.eckelp.lancamentoconta.categoria.usecase;

import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.app.security.repository.IUsuarioRepository;
import br.eckelp.lancamentoconta.app.security.service.EncoderService;
import br.eckelp.lancamentoconta.categoria.CategoriaCenarioTest;
import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.dominio.interfaces.IBuscarTodasCategoriasUseCase;
import br.eckelp.lancamentoconta.categoria.infra.ICategoriaRepository;
import br.eckelp.lancamentoconta.usuario.UsuarioCenarioTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class BuscarTodasCategoriasUseCaseTest {

    @Autowired
    private ICategoriaRepository repository;
    @Autowired
    private IBuscarTodasCategoriasUseCase buscarTodasCategoriasUseCase;
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private EncoderService encoderService;

    private CategoriaCenarioTest cenario;
    private UsuarioCenarioTest cenarioUsuario;

    @Before
    public void cenario(){
        this.cenario = new CategoriaCenarioTest(repository);
        this.cenarioUsuario = new UsuarioCenarioTest(encoderService, usuarioRepository);
    }

    @Test
    public void deveRetornarListaDeCategorias(){
        Usuario usuario = this.cenarioUsuario.getUsuario();
        this.cenario.criarListaCategoriasValidas(usuario);

        List<Categoria> categorias = buscarTodasCategoriasUseCase.buscarTodas(usuario);

        Assert.notEmpty(categorias, "Nenhuma categoria foi encontrada");
    }
}
