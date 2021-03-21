package br.eckelp.lancamentoconta.app.security.service.validacoes.usuario;

import br.eckelp.lancamentoconta.app.exception.EmailInvalidoException;
import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.app.security.dominio.UsuarioCadastroValidacao;
import br.eckelp.lancamentoconta.app.security.repository.IUsuarioRepository;
import org.springframework.stereotype.Component;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Component
public class UsuarioCadastroValidacaoEmailUnico implements UsuarioCadastroValidacao {

    private final IUsuarioRepository usuarioRepository;

    public UsuarioCadastroValidacaoEmailUnico(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void validar(Usuario usuario) {
        if(isEmpty(usuario.getEmail())){
            throw new EmailInvalidoException("E-mail inválido!");
        }

        Integer totalUsuariosComEsteEmail = usuarioRepository.buscarTotalDeUsuariosPorEmail(usuario.getEmail());

        if(totalUsuariosComEsteEmail > 0){
            throw new EmailInvalidoException("Este e-mail já está em uso");
        }
    }
}
