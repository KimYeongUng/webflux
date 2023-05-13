package com.example.webflux.repo;


import com.example.webflux.domain.Data;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface DataRepository extends ReactiveCrudRepository<Data,Long> {
    Mono<Data> findByDataId(Long dataId);
}
