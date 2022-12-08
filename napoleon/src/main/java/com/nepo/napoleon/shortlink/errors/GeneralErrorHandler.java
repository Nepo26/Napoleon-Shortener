package com.nepo.napoleon.shortlink.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

@ControllerAdvice
public class GeneralErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {MalformedURLException.class, URISyntaxException.class})
    protected ResponseEntity<Object> nonUriSuppliedExceptionHandler(final RuntimeException e, final WebRequest request) {

        return ResponseEntity.badRequest().body("Non compliant URI");
    }
}
