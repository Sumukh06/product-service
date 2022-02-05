package com.learningreactive.productservice.service;

import com.learningreactive.productservice.domain.ProductDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class DataService implements CommandLineRunner {
    @Autowired
    ProductService productService;
    @Override
    public void run(String... args) throws Exception {
        ProductDomain p1=new ProductDomain("Laptop",57000);
        ProductDomain p2=new ProductDomain("Router",1900);
        ProductDomain p3=new ProductDomain("Mouse",699);
        ProductDomain p4=new ProductDomain("4k tv",35000);
        ProductDomain p5=new ProductDomain("Headphone",2999);

        Flux.just(p1,p2,p3,p4,p5)
                .flatMap(domain -> productService.insertProduct(Mono.just(domain)))
                .subscribe(System.out::println);
    }
}
