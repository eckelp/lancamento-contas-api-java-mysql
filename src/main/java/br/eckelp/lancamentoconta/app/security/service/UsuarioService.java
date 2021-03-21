package br.eckelp.lancamentoconta.app.security.service;

import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.app.security.repository.IUsuarioRepository;
import br.eckelp.lancamentoconta.app.security.service.validacoes.usuario.UsuarioCadastroValidacoes;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioCadastroValidacoes validacoes;
    private final IEncoder encoderService;
    private final IUsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioCadastroValidacoes validacoes, IEncoder encoderService, IUsuarioRepository usuarioRepository) {
        this.validacoes = validacoes;
        this.encoderService = encoderService;
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrar(Usuario usuario) {

        this.validacoes.validar(usuario);

        usuario.codificarSenha(encoderService);

        Usuario usuarioCadastrado = usuarioRepository.save(usuario);

        return usuarioCadastrado;
    }
}
