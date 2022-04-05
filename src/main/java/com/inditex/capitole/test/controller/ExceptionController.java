package com.inditex.capitole.test.controller;

import com.inditex.capitole.test.dto.ResponseGenericDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Log4j2
@RestControllerAdvice
public class ExceptionController
{
    public static final String FAILED = "Failed";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseGenericDto<String> manageGenericException(Exception ex, WebRequest request)
    {
        log.error("ERROR Exception", ex);
        return new ResponseGenericDto<>(1, FAILED, "Internal Error");
    }
}
