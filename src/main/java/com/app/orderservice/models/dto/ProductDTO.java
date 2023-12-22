package com.app.orderservice.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
    private String id;
    private String description;
    private Double price;

    public ProductDTO(String description, Double price) {
        this.description = description;
        this.price = price;
    }
}
