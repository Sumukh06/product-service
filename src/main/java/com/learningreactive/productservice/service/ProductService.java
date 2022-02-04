package com.learningreactive.productservice.service;

import com.learningreactive.productservice.domain.ProductDomain;
import com.learningreactive.productservice.repository.ProductRepository;
import com.learningreactive.productservice.util.EntityDomainUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    @Autowired
    ProductRepository repository;

    public Mono<ProductDomain> getById(String id){
        return repository.findById(id)
                .map(EntityDomainUtil::toDomain);
    }
    public Flux<ProductDomain> getAllProducts(){
        return repository.findAll()
                .map(EntityDomainUtil::toDomain);
    }
    public Mono<ProductDomain> insertProduct(Mono<ProductDomain> productDomainMono){
       return productDomainMono
               .map(EntityDomainUtil::toEntity)
               .flatMap(product -> repository.insert(product))
               .map(EntityDomainUtil::toDomain);
    }
    public Mono<ProductDomain> updateProduct(String id,Mono<ProductDomain> productDomainMono){
        return repository.findById(id)
                .flatMap(product -> productDomainMono
                        .map(EntityDomainUtil::toEntity)
                        .doOnNext(e->e.setId(id)))
                .flatMap(repository::save)
                .map(EntityDomainUtil::toDomain);
    }
    public Mono<Void> deleteProduct(String id){
        return repository.deleteById(id);
    }
}
