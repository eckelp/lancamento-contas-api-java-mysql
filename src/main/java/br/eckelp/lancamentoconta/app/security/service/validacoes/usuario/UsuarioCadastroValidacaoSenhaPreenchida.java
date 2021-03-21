package br.eckelp.lancamentoconta.app.security.service.validacoes.usuario;

import br.eckelp.lancamentoconta.app.exception.EmailInvalidoException;
import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.app.security.dominio.UsuarioCadastroValidacao;
import org.springframework.stereotype.Component;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Component
public class UsuarioCadastroValidacaoSenhaPreenchida implements UsuarioCadastroValidacao {

    @Override
    public void validar(Usuario usuario) {
        if(isEmpty(usuario.getSenha())){
            throw new EmailInvalidoException("Senha inválido!");
        }
    }
}
