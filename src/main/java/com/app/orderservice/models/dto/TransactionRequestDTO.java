package com.app.orderservice.models.dto;


import lombok.Data;

@Data
public class TransactionRequestDTO {
    private Integer userId;
    private Double amount;
}
