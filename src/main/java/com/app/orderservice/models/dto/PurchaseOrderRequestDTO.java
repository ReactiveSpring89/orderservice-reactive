package com.app.orderservice.models.dto;

import lombok.Data;

@Data
public class PurchaseOrderRequestDTO {
    private Integer userId;
    private String productId;
}
