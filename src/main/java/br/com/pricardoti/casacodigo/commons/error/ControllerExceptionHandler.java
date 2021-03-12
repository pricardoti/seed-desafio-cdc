package br.com.pricardoti.casacodigo.commons.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetail> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException argumentNotValidException
    ) {
        Set<ItemErrorDetail> errors = argumentNotValidException.getBindingResult()
                .getAllErrors().stream()
                .map((error) -> new ItemErrorDetail(((FieldError) error).getField(), error.getDefaultMessage()))
                .collect(toSet());

        return ResponseEntity.badRequest()
                .body(new ErrorDetail
                        .Builder(HttpStatus.BAD_REQUEST.value(), "Argumento do método não é valido.")
                        .description("Erro na validação de campo(s).")
                        .errors(errors)
                        .timestamp(LocalDateTime.now())
                        .build());
    }
}