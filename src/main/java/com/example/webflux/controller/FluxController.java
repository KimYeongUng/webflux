package com.example.webflux.controller;

import com.example.webflux.domain.Event;

import java.util.*;

import com.example.webflux.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class FluxController {

    FluxController(DataService service){
    }

    @GetMapping("/")
    Mono<String> hello(){
      log.info("Mono");
      String msg = generateHello();
      return Mono.just(msg);
    }

    @GetMapping("/monolist")
    Mono<List<Event>> hello2(){
        List<Event> events = Arrays.asList(new Event(1L,"value"),new Event(2L,"value"));
        return Mono.just(events);
    }

    @GetMapping("/event/{id}")
    Mono<Event> event(@PathVariable long id){
        return Mono.just(new Event(id,"event: "+id));
    }

    private String generateHello() {
        return "Hello webFlux";
    }

}
