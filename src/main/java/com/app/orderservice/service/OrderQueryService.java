package com.app.orderservice.service;

import com.app.orderservice.models.dto.PurchaseOrderResponseDTO;
import reactor.core.publisher.Flux;

public interface OrderQueryService {
    Flux<PurchaseOrderResponseDTO> getProductsByUserId(Integer userId);
}
