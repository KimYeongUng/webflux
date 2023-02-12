package com.example.webflux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@SpringBootApplication
@RestController
@Slf4j
public class WebfluxApplication {

    @GetMapping("/")
    Mono<String> hello(){
        log.info("pos1");
        String msg = generateHello();
        //Mono<String> m = Mono.just(msg).doOnNext(log::info).log();
        log.info("pos2");
        return Mono.just(msg);
    }

    @GetMapping("/monolist")
    Mono<List<Event>> hello2(){
        List<Event> list = Arrays.asList(new Event(1L,"event1"),new Event(2L,"evnet2"));
        return Mono.just(list);
    }

    @GetMapping("/event/{id}")
    Mono<Event> event(@PathVariable long id){
        return Mono.just(new Event(id,"event "+id));
    }

    @GetMapping(value = "/events",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Event> events(){
        Flux<String> es = Flux.generate(sink->sink.next("value"));
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));

        return Flux.zip(es,interval).map(tuple -> new Event(tuple.getT2()+1,tuple.getT1())).take(10);
    }

    private String generateHello() {
        log.info("generateHello()");
        return "Hello Webflux";
    }

    @Data
    @AllArgsConstructor
    public static class Event{
        long id;
        String value;
    }

    public static void main(String[] args) {
        SpringApplication.run(WebfluxApplication.class, args);
    }
}
