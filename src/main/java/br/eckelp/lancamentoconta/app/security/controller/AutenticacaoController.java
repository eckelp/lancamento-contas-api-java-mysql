package br.eckelp.lancamentoconta.app.security.controller;

import br.eckelp.lancamentoconta.app.security.controller.dto.UsuarioDto;
import br.eckelp.lancamentoconta.app.security.controller.form.UsuarioForm;
import br.eckelp.lancamentoconta.app.security.service.AutenticacaoService;
import br.eckelp.lancamentoconta.app.security.service.JwtTokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;
    private final JwtTokenService jwtTokenService;

    public AutenticacaoController(AutenticacaoService autenticacaoService, JwtTokenService jwtTokenService) {
        this.autenticacaoService = autenticacaoService;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping
    public ResponseEntity autenticar(@RequestBody @Valid UsuarioForm form) {

        try {
            UsuarioDto usuarioDto = autenticacaoService.autenticar(form.getEmail(), form.getSenha());

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwtTokenService.gerarToken(usuarioDto)
                    )
                    .body(usuarioDto);

        } catch (JsonProcessingException | BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }



}
