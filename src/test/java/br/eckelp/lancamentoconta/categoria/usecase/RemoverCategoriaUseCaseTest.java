package br.eckelp.lancamentoconta.categoria.usecase;


import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.app.security.repository.IUsuarioRepository;
import br.eckelp.lancamentoconta.app.security.service.EncoderService;
import br.eckelp.lancamentoconta.categoria.CategoriaCenarioTest;
import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.dominio.interfaces.IRemoverCategoriaUseCase;
import br.eckelp.lancamentoconta.categoria.infra.ICategoriaRepository;
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

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class RemoverCategoriaUseCaseTest {

    @Autowired
    private ICategoriaRepository repository;

    @Autowired
    private IRemoverCategoriaUseCase removerCategoriaUseCase;
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
    public void deveRemoverUmaCategoria(){
        Usuario usuario = this.cenarioUsuario.getUsuario();
        Categoria categoria = this.cenario.criarUmaCategoria("Remover", usuario);

        removerCategoriaUseCase.remover(categoria.getId());

        Optional<Categoria> categoriaOptional = repository.findById(categoria.getId());

        Assert.assertTrue(categoriaOptional.isEmpty());

    }

}