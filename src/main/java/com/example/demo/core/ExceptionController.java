package com.example.demo.core;

import com.example.demo.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;


@Slf4j
@ControllerAdvice
@ResponseBody
public class ExceptionController {

    private static final String SERVER_ERROR_MESSAGE = "Bad parameter.";

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException ex) {
        return new ResponseEntity<>(ErrorResponse.getInstance(ex.getMessage()), ex.getCode());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        return new ResponseEntity<>(ErrorResponse.getInstance(ex.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponses> handleBindException(BindException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        return new ResponseEntity<>(ErrorResponses.getInstance(buildMessage(bindingResult)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponses> methodArgumentNotValidHandler(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        return new ResponseEntity<>(ErrorResponses.getInstance(buildMessage(bindingResult)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleApiException(Throwable t) {
        log.error("Server error", t);
        return new ResponseEntity<>(ErrorResponse.getInstance(t.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private List<String> buildMessage(BindingResult bindingResult) {
        return bindingResult.hasErrors() ?
                getValidationErrors(bindingResult) : newArrayList(SERVER_ERROR_MESSAGE);
    }

    private List<String> getValidationErrors(BindingResult bindingResult) {
        return bindingResult.getAllErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
    }
}
