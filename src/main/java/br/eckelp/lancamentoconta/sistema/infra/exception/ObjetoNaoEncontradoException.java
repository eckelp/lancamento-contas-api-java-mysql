package br.eckelp.lancamentoconta.sistema.infra.exception;

import br.eckelp.lancamentoconta.sistema.infra.exception.handler.ApiException;

public class ObjetoNaoEncontradoException extends ApiException {

    public ObjetoNaoEncontradoException(String message) {
        super(message);
    }
}
