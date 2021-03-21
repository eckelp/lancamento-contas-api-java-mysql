package br.eckelp.lancamentoconta.usuario;

import br.eckelp.lancamentoconta.app.security.context.UsuarioContext;
import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.app.security.repository.IUsuarioRepository;
import br.eckelp.lancamentoconta.app.security.service.EncoderService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UsuarioCenarioTest {

    public static final String SENHA_USUARIO = "123456";

    private final EncoderService encoder;
    private final IUsuarioRepository usuarioRepository;

    public UsuarioCenarioTest(EncoderService encoder, IUsuarioRepository usuarioRepository) {
        this.encoder = encoder;
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario getUsuario(){
        Usuario usuario = usuarioRepository.save(
                Usuario.builder()
                        .id(1L)
                        .nome("Usuario")
                        .sobrenome("Teste")
                        .email("teste@email.com")
                        .senha(encoder.encode(SENHA_USUARIO))
                        .build()
        );

        UsuarioContext.setUsuario(usuario);

        return usuario;
    }

}
