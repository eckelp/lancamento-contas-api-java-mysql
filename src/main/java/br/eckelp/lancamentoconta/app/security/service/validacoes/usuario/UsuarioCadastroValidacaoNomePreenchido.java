package br.eckelp.lancamentoconta.app.security.service.validacoes.usuario;

import br.eckelp.lancamentoconta.app.exception.EmailInvalidoException;
import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.app.security.dominio.UsuarioCadastroValidacao;
import org.springframework.stereotype.Component;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Component
public class UsuarioCadastroValidacaoNomePreenchido implements UsuarioCadastroValidacao {

    @Override
    public void validar(Usuario usuario) {
        if(isEmpty(usuario.getNome())){
            throw new EmailInvalidoException("Nome inválido!");
        }

        if(isEmpty(usuario.getSobrenome())){
            throw new EmailInvalidoException("Sobrenome inválido!");
        }


    }
}
