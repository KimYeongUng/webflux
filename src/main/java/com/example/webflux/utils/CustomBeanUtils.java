package com.example.webflux.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Collection;

@Component
public class CustomBeanUtils<T> {
    public T copyNonNullProperties(T source,T destination){
        if(source == null || destination == null || source.getClass() != destination.getClass() )
            return null;

        final BeanWrapper src = new BeanWrapperImpl(source);
        final BeanWrapper dest = new BeanWrapperImpl(destination);

        for (final Field property: source.getClass().getDeclaredFields()){
            Object proviedObj = src.getClass().getDeclaredFields();
            if(proviedObj != null && !(proviedObj instanceof Collection<?>)){
                dest.setPropertyValue(property.getName(),proviedObj);
            }
        }

        return destination;
    }
}
