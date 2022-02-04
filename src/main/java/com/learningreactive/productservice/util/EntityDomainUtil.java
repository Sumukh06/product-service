package com.learningreactive.productservice.util;

import com.learningreactive.productservice.domain.ProductDomain;
import com.learningreactive.productservice.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class EntityDomainUtil {
    public static ProductDomain toDomain(Product product){
        ProductDomain domain=new ProductDomain();
        domain.setId(product.getId());
        domain.setDescription(product.getDescription());
        domain.setPrice(product.getPrice());
        return domain;
    }
    public static Product toEntity(ProductDomain domain){
        Product product=new Product();
        product.setId(domain.getId());
        product.setDescription(domain.getDescription());
        product.setPrice(domain.getPrice());
        return product;
    }
}
