package br.eckelp.lancamentoconta.app.security.filter;

import br.eckelp.lancamentoconta.app.security.context.UsuarioContext;
import br.eckelp.lancamentoconta.app.security.controller.dto.UsuarioDto;
import br.eckelp.lancamentoconta.app.security.dominio.Perfil;
import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.app.security.service.JwtTokenService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Component
public class AutenticacaoPorTokenFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;

    public AutenticacaoPorTokenFilter(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {


        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        // Get jwt token and validate
        final String token = header.substring(7).trim();

        if (!jwtTokenService.autenticar(token)) {
            chain.doFilter(request, response);
            return;
        }

        UsuarioDto usuarioAutenticacao = jwtTokenService.getUsuarioAutenticacao(token);

        if(usuarioAutenticacao != null) {

            UsuarioContext.setUsuario(Usuario.builder().id(usuarioAutenticacao.getId()).build());

            List<Perfil> authorities = usuarioAutenticacao.getPerfis();

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(usuarioAutenticacao.getEmail(), null, authorities);

            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);

    }
}
