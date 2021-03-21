package br.eckelp.lancamentoconta.app.security.controller.dto;

import br.eckelp.lancamentoconta.app.security.dominio.Perfil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private List<Perfil> perfis;

}
