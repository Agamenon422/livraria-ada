package tec.ada.livrariaada.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tec.ada.livrariaada.util.ApiError;
import tec.ada.livrariaada.util.ApiFieldError;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApplicationControllerAdvice extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        System.out.println("passou aqui OSMAEL \n\n\n\n");
        List<ApiError> erros = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            String mensagem = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String sigla = fieldError.getField().replaceAll("[^.a-zA-Z]", "");
            erros.add(new ApiFieldError(HttpStatus.BAD_REQUEST.value(), mensagem, sigla));
        });

        return handleExceptionInternal(ex, erros.stream().findFirst(), headers, HttpStatus.BAD_REQUEST, request);
    }

}
