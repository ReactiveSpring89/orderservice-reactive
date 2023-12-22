package com.app.orderservice.service;

import com.app.orderservice.models.dto.PurchaseOrderRequestDTO;
import com.app.orderservice.models.dto.PurchaseOrderResponseDTO;
import reactor.core.publisher.Mono;

public interface OrderFulfillmentService {
    Mono<PurchaseOrderResponseDTO> processOrder(Mono<PurchaseOrderRequestDTO> purchaseOrderRequestDTOMono);
}
