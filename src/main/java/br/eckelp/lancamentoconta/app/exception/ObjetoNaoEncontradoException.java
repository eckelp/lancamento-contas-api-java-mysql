package br.eckelp.lancamentoconta.app.exception;

import br.eckelp.lancamentoconta.app.exception.handler.ApiException;

public class ObjetoNaoEncontradoException extends ApiException {

    public ObjetoNaoEncontradoException(String message) {
        super(message);
    }
}
