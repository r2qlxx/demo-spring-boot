package com.example.demo.application.controllers;

import com.example.demo.application.resources.ErrorRes;
import com.example.demo.domain.common.AppLogger;
import com.example.demo.domain.exceptions.SystemException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(SystemException.class)
//  @ExceptionHandler({DemoException1.class, DemoException2.class})
    public ResponseEntity<ErrorRes> handleException(SystemException e) {
        String errMsg = AppLogger.readAppLogMsg("APP_ERROR_LOG_002", e.getArgs());
        ErrorRes errorRes = new ErrorRes(e.getErrorCode(), errMsg);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<ErrorRes>(errorRes, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
