package com.gameawards.apigameawards.controller;

import com.gameawards.apigameawards.service.exception.NoContentException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public abstract class BaseRestController {

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<Void> handlerNoContentBusinessException (NoContentException exception) {
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<ApiErrorDto> handlerBusinessException (NoContentException exception) {
        ApiErrorDto error = new ApiErrorDto(exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<ApiErrorDto> handlerUnexpectedException(Throwable exception) {
        exception.printStackTrace();
        ApiErrorDto error = new ApiErrorDto("Erro inesperado");
        return ResponseEntity.internalServerError().body(error);
    }
}
