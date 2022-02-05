package com.learningreactive.productservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDomain {
    private String id;
    private String description;
    private Integer price;

    public ProductDomain(String description, Integer price) {
        this.description = description;
        this.price = price;
    }
}
