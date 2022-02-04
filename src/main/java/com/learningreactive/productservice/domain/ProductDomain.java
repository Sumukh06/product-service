package com.learningreactive.productservice.domain;

import lombok.Data;

@Data
public class ProductDomain {
    private String id;
    private String description;
    private Integer price;
}
