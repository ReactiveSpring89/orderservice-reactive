package com.app.orderservice.controller;

import com.app.orderservice.models.dto.PurchaseOrderRequestDTO;
import com.app.orderservice.models.dto.PurchaseOrderResponseDTO;
import com.app.orderservice.service.OrderFulfillmentService;
import com.app.orderservice.service.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("order")
public class PurchaseOrderController {
    @Autowired
    private OrderFulfillmentService orderFulfillmentService;

    @Autowired
    private OrderQueryService orderQueryService;

    @PostMapping
    public Mono<ResponseEntity<PurchaseOrderResponseDTO>> createOrder(@RequestBody Mono<PurchaseOrderRequestDTO> purchaseOrderRequestDTOMono) {
        return orderFulfillmentService.processOrder(purchaseOrderRequestDTOMono)
                .map(ResponseEntity::ok)
                .onErrorReturn(WebClientResponseException.class,
                        ResponseEntity.badRequest().build())
                .onErrorReturn(WebClientRequestException.class,
                        ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build());
    }

    @GetMapping("/user/{userId}")
    public Flux<PurchaseOrderResponseDTO> getOrdersByUserId(@PathVariable Integer userId) {
        return orderQueryService.getProductsByUserId(userId);
    }


}
