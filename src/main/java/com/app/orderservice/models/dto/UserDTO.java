package com.app.orderservice.models.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String name;
    private Double balance;
}
