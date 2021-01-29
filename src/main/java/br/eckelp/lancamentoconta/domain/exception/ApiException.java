package br.eckelp.lancamentoconta.domain.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiException extends RuntimeException {

    private HttpStatus status;

    public ApiException(String message) {
        this(message, HttpStatus.BAD_REQUEST);
    }

    public ApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
