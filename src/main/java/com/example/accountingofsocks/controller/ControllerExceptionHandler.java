package com.example.accountingofsocks.controller;

import com.example.accountingofsocks.exception.NullQuantityPointerException;
import com.example.accountingofsocks.exception.QuantitySocksOutOfBoundsException;
import com.example.accountingofsocks.exceptionMapping.SocksIncorrectData;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    ResponseEntity<SocksIncorrectData> handlerException(QuantitySocksOutOfBoundsException exception){
        SocksIncorrectData data = new SocksIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    ResponseEntity<SocksIncorrectData> handlerException(NullQuantityPointerException exception){
        SocksIncorrectData data = new SocksIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    ResponseEntity<SocksIncorrectData> handlerException(MethodArgumentNotValidException exception){
        SocksIncorrectData data = new SocksIncorrectData();
        data.setInfo(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    ResponseEntity<SocksIncorrectData> handlerException (JsonParseException exception){
        SocksIncorrectData data = new SocksIncorrectData();
        data.setInfo("Параметры запроса отсутствуют или имеют некорректный формат;");
        return new ResponseEntity<>(data,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    ResponseEntity<SocksIncorrectData> handlerException (MissingServletRequestParameterException exception){
        SocksIncorrectData data = new SocksIncorrectData();
        data.setInfo("Параметры запроса отсутствуют или имеют некорректный формат;");
        return new ResponseEntity<>(data,HttpStatus.BAD_REQUEST);
    }
}
