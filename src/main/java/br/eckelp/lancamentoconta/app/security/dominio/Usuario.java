package br.eckelp.lancamentoconta.app.security.dominio;

import br.eckelp.lancamentoconta.app.security.controller.dto.UsuarioDto;
import br.eckelp.lancamentoconta.app.security.service.IEncoder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String sobrenome;
    private String email;
    private String senha;

    @OneToMany
    @JoinTable(name = "usuario_perfil")
    private List<Perfil> perfis;

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return perfis;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UsuarioDto converterParaDto() {

        return UsuarioDto.builder()
                .id(id)
                .nome(nome)
                .sobrenome(sobrenome)
                .email(email)
                .perfis(perfis)
                .build();

    }

    public void codificarSenha(IEncoder encoderService) {
        this.senha = encoderService.encode(senha);
    }


}
