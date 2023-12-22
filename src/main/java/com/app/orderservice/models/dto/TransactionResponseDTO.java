package com.app.orderservice.models.dto;


import com.app.orderservice.models.enums.TransactionStatus;
import lombok.Data;

@Data
public class TransactionResponseDTO {
    private Integer userId;
    private Double amount;
    private TransactionStatus status;
}
