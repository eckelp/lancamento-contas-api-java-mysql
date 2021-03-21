package br.eckelp.lancamentoconta.app.security.controller.form;

import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UsuarioCadastroForm {

    @NotNull
    @NotBlank
    @NotEmpty
    private String email;

    @NotNull
    @NotBlank
    @NotEmpty
    private String nome;


    @NotNull
    @NotBlank
    @NotEmpty
    private String sobrenome;

    @NotNull
    @NotBlank
    @NotEmpty
    private String senha;

    public Usuario converterUsuario() {
        return Usuario.builder()
                .nome(nome)
                .sobrenome(sobrenome)
                .email(email)
                .senha(senha)
                .build();
    }
}
