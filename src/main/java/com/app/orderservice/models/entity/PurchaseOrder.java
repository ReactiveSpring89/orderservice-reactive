package com.app.orderservice.models.entity;

import com.app.orderservice.models.enums.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PurchaseOrder {
    @Id
    @GeneratedValue
    private Integer id;
    private String productId;
    private Integer userId;
    private Double amount;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;
}
