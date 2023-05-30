package com.example.webflux.service;

import com.example.webflux.domain.Data;
import com.example.webflux.exception.BusinessLogicException;
import com.example.webflux.exception.ExceptionCode;
import com.example.webflux.repo.DataRepository;
import com.example.webflux.utils.CustomBeanUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.List;

@Slf4j
@Service("dataService")
@RequiredArgsConstructor
public class DataService {
    private final @NonNull DataRepository repository;
    private final @NonNull CustomBeanUtils<Data> beanUtils;

    public Mono<Data> saveData(Data data){
        return verifyExistsDataId(data.getDataid())
                .then(repository.save(data));
    }

    public Mono<Data> findData(Long dataId){
        return verifyFindData(dataId);
    }

    public Mono<Data> updateData(Data data){
        return verifyFindData(data.getDataid())
                .map(f->beanUtils.copyNonNullProperties(data,f))
                .flatMap(repository::save);
    }

    public Mono<List<Data>> getAllData(){
        return repository.findAll().collectList();
    }

    private Mono<Void> verifyExistsDataId(Long dataid) {
        return repository.findByDataId(dataid)
                .flatMap(f-> {
                    if (f != null)
                        return Mono.error(new BusinessLogicException(ExceptionCode.DATA_EXISTS));
                    return Mono.empty();
                });
    }

    private Mono<Data> verifyFindData(Long dataId){
        return repository.findByDataId(dataId)
                .switchIfEmpty(Mono.error(new BusinessLogicException(ExceptionCode.DATA_NOT_FOUND)));
    }
}
