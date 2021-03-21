package br.eckelp.lancamentoconta.app.exception;

import br.eckelp.lancamentoconta.app.exception.handler.ApiException;

public class EmailInvalidoException extends ApiException {

    public EmailInvalidoException(String message) {
        super(message);
    }
}
