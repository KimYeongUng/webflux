package com.example.webflux.exception;

import lombok.Getter;

public class BusinessLogicException extends RuntimeException{

    @Getter
    private ExceptionCode exceptionCode;
    public BusinessLogicException(ExceptionCode code){
        super();
    }
}
