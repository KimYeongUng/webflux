package com.example.webflux.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.xml.validation.Validator;

@Slf4j
@Component("dataValidator")
public class DataValidator<T> {
    private final Validator validator;

    public DataValidator(Validator validator){
        this.validator = validator;
    }

}
