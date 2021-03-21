package br.eckelp.lancamentoconta.app.security.controller.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioForm {

    @NotEmpty
    @NotNull
    @NotBlank
    private String email;

    @NotEmpty
    @NotNull
    @NotBlank
    private String senha;

}
