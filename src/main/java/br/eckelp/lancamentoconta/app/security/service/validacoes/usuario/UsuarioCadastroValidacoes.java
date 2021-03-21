package br.eckelp.lancamentoconta.app.security.service.validacoes.usuario;

import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.app.security.dominio.UsuarioCadastroValidacao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioCadastroValidacoes {


    private final List<UsuarioCadastroValidacao> validacoes;

    public UsuarioCadastroValidacoes(List<UsuarioCadastroValidacao> validacoes) {
        this.validacoes = validacoes;
    }

    public void validar(Usuario usuario){
        this.validacoes.forEach(validacao -> validacao.validar(usuario));
    }
}
