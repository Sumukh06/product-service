package com.learningreactive.productservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class Product {
    @Id
    private String id;
    private String description;
    private Integer price;
}
