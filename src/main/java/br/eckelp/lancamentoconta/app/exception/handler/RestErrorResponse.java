package br.eckelp.lancamentoconta.app.exception.handler;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RestErrorResponse {

    private String mensagem;
    private LocalDateTime data;

    public RestErrorResponse(String mensagem) {
        this.mensagem = mensagem;
        this.data = LocalDateTime.now();
    }
}
