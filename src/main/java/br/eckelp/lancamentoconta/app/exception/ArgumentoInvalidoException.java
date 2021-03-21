package br.eckelp.lancamentoconta.app.exception;

import br.eckelp.lancamentoconta.app.exception.handler.ApiException;

public class ArgumentoInvalidoException extends ApiException {

    public ArgumentoInvalidoException(String message) {
        super(message);
    }
}
