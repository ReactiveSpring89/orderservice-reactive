package com.app.orderservice.models.dto;

import com.app.orderservice.models.enums.OrderStatus;
import lombok.Data;

@Data
public class PurchaseOrderResponseDTO {
    private Integer userId;
    private String productId;
    private Integer orderId;
    private Double amount;
    private OrderStatus status;
}
