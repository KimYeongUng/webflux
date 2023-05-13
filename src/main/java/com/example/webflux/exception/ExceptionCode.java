package com.example.webflux.exception;

import lombok.Getter;

public enum ExceptionCode {
    DATA_NOT_FOUND(404,"Data Not Found"),
    DATA_EXISTS(409,"Data Exists");

    @Getter
    private int status;

    @Getter
    private String msg;

    ExceptionCode(int status,String msg){
        this.status = status;
        this.msg = msg;
    }
}
