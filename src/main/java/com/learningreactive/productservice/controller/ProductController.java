package com.learningreactive.productservice.controller;

import com.learningreactive.productservice.domain.ProductDomain;
import com.learningreactive.productservice.service.ProductService;
import com.learningreactive.productservice.util.EntityDomainUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/all")
    public Flux<ProductDomain> al(){
        return productService.getAllProducts();
    }
    @GetMapping("{id}")
    public Mono<ResponseEntity<ProductDomain>>getByid(@PathVariable String id){
        return productService.getById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @PostMapping()
    public Mono<ProductDomain> insertProduct(@RequestBody Mono<ProductDomain> productDomainMono){
        return productService.insertProduct(productDomainMono);
    }
    @PutMapping("/{id}")
    public Mono<ResponseEntity<ProductDomain>> update(@PathVariable String id,
                                                      @RequestBody Mono<ProductDomain> productDomainMono){
        return productService.updateProduct(id,productDomainMono)
                .map(domain -> ResponseEntity.status(HttpStatus.ACCEPTED).body(domain))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return productService.deleteProduct(id);
    }
    @GetMapping("/price-range")
    public Flux<ProductDomain> priceRange(@RequestParam("min")Integer min,
                                          @RequestParam("max")Integer max){
        return productService.getAllProducts()
                .filter(domain -> domain.getPrice()>=min && domain.getPrice()<=max)
                .map(EntityDomainUtil::toEntity)
                .map(EntityDomainUtil::toDomain);
    }
}
