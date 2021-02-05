package br.eckelp.lancamentoconta.sistema.infra.exception;

import br.eckelp.lancamentoconta.sistema.infra.exception.handler.ApiException;

public class ArgumentoInvalidoException extends ApiException {

    public ArgumentoInvalidoException(String message) {
        super(message);
    }
}
