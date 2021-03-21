package br.eckelp.lancamentoconta.app.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Object> handleObjetoNaoEncontradoException(ApiException ex, WebRequest request) {

        RestErrorResponse resposta = new RestErrorResponse(ex.getMessage());

        return handleExceptionInternal(ex, resposta, new HttpHeaders(), ex.getStatus(), request);
    }
}
