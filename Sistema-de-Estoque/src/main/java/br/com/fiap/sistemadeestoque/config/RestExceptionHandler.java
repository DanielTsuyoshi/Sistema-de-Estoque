package br.com.fiap.sistemadeestoque.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.sistemadeestoque.exceptions.RestError;

@RestControllerAdvice
public class RestExceptionHandler {

    Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestError> argumentExceptionHandler(){
        log.error("Erro de argumento invalido");
        return ResponseEntity.badRequest().body(new RestError(400, "Campo inválido"));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<RestError> responseStatusExceptionHandler(ResponseStatusException e) {
        return ResponseEntity.status(e.getStatusCode()).body(
            new RestError(e.getStatusCode().value(), e.getBody().getDetail())
        );
    }
    
}
