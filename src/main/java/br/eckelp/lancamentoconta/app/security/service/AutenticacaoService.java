package br.eckelp.lancamentoconta.app.security.service;

import br.eckelp.lancamentoconta.app.security.controller.dto.UsuarioDto;
import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.app.security.repository.IUsuarioRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

@Service
public class AutenticacaoService implements UserDetailsService {

    private final AuthenticationManager authenticationManager;
    private final IUsuarioRepository repository;

    public AutenticacaoService(@Lazy AuthenticationManager authenticationManager, IUsuarioRepository repository) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) {
        Optional<Usuario> usuario = repository.findByEmail(login);

        return usuario.orElseThrow(() -> new UsernameNotFoundException(format("Usuário '%s' não encontrado", login)));
    }

    public UsuarioDto autenticar(String username, String senha) {
        Authentication usuarioAutenticado = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, senha));

        Usuario usuario = (Usuario) usuarioAutenticado.getPrincipal();

        return usuario.converterParaDto();
    }
}
