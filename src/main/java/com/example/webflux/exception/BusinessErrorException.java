package com.example.webflux.exception;

import lombok.Getter;

public class BusinessErrorException extends RuntimeException{

    @Getter
    private ExceptionCode exceptionCode;
    public BusinessErrorException(ExceptionCode code){
        super();
    }
}
