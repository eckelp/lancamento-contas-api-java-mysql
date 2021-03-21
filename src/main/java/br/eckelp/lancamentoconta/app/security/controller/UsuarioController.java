package br.eckelp.lancamentoconta.app.security.controller;

import br.eckelp.lancamentoconta.app.security.controller.dto.UsuarioDto;
import br.eckelp.lancamentoconta.app.security.controller.form.UsuarioCadastroForm;
import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.app.security.service.JwtTokenService;
import br.eckelp.lancamentoconta.app.security.service.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final JwtTokenService jwtTokenService;

    public UsuarioController(UsuarioService usuarioService, JwtTokenService jwtTokenService) {
        this.usuarioService = usuarioService;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> cadastrar(@RequestBody UsuarioCadastroForm form) throws JsonProcessingException {
        Usuario usuarioCadastrado = usuarioService.cadastrar(form.converterUsuario());

        UsuarioDto usuarioDto = usuarioCadastrado.converterParaDto();

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.AUTHORIZATION,
                        jwtTokenService.gerarToken(usuarioDto)
                ).body(usuarioDto);

    }

}
