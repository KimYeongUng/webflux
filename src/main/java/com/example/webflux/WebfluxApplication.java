package com.example.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;


@SpringBootApplication
public class WebfluxApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebfluxApplication.class, args);
    }

    // Router Functions Model
    @Bean
    RouterFunction<ServerResponse> route(){
        return RouterFunctions.route(GET("/route"), request -> ok().body(Flux.just("Hello","World"),String.class));
    }

//    @Bean
//    RouterFunction<ServerResponse> routeStatic(){
//        return route(GET("/route"),req->ok().body(Flux.just("Hello","World"),String.class));
//    }

}