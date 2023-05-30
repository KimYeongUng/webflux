package com.example.webflux.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Event {

    @Builder
    public Event(long id,String value){
        this.id = id;
        this.value = value;
    }

    long id;
    String value;
}
