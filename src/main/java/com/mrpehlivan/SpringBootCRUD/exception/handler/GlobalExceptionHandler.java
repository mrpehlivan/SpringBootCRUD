package com.mrpehlivan.SpringBootCRUD.exception.handler;


import com.google.common.collect.Maps;
import com.mrpehlivan.SpringBootCRUD.exception.CRUDException;
import com.mrpehlivan.SpringBootCRUD.exception.MissingArgumentException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {MissingArgumentException.class})
    public Map<String, Object> handleException(CRUDException e) {
        Map<String, Object> responseBody = Maps.newHashMap();
        responseBody.put("status", e.status());
        responseBody.put("code", e.errorCode());
        responseBody.put("message", e.errorMessage());
        return responseBody;
    }
}