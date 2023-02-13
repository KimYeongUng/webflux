package com.example.webflux.controller;

import com.example.webflux.event.Event;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class FluxController {

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

    @GetMapping(value = "/events",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Event> event(){
        Flux<String> es = Flux.generate(s->s.next("value"));
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));

        return Flux.zip(es,interval)
                .map(tuple->new Event(tuple.getT2()+1,tuple.getT1()))
                .take(10);
    }

    private String generateHello() {
        return "Hello webFlux";
    }

}
