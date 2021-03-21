package br.eckelp.lancamentoconta.app.security.service;

import br.eckelp.lancamentoconta.app.security.controller.dto.UsuarioDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtTokenService {

    @Value("${security.chave}")
    private String chave;

    @Value("${security.issuer}")
    private String issuer;

    private final ObjectMapper mapper;

    public JwtTokenService() {
        this.mapper = new ObjectMapper();
    }

    public String gerarToken(UsuarioDto usuarioDto) throws JsonProcessingException {

        Date hoje = new Date();
        Date dataExpiracao = Date.from(LocalDateTime.now().plusDays(300).atZone(ZoneId.systemDefault()).toInstant());

        String usuarioEmJson = this.mapper.writeValueAsString(usuarioDto);

        return Jwts.builder()
                .setSubject(usuarioEmJson)
                .setIssuer(issuer)
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, chave).compact();
    }

    public UsuarioDto getUsuarioAutenticacao(String token) throws JsonProcessingException {

        Claims claims = Jwts.parser()
                .setSigningKey(chave)
                .parseClaimsJws(token)
                .getBody();

        return mapper.readValue(claims.getSubject(), UsuarioDto.class);

    }

    public boolean autenticar(String token) {

        try{
            Jwts.parser().setSigningKey(chave).parseClaimsJws(token);
            return true;
        }catch (Exception e) {
            return false;
        }

    }
}
