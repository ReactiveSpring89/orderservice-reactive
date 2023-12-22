package com.app.orderservice.controller;

import com.app.orderservice.models.dto.PurchaseOrderRequestDTO;
import com.app.orderservice.models.dto.PurchaseOrderResponseDTO;
import com.app.orderservice.service.OrderFulfillmentService;
import com.app.orderservice.service.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public Mono<PurchaseOrderResponseDTO> createOrder(@RequestBody Mono<PurchaseOrderRequestDTO> purchaseOrderRequestDTOMono) {
        return orderFulfillmentService.processOrder(purchaseOrderRequestDTOMono);
    }

    @GetMapping("/user/{userId}")
    public Flux<PurchaseOrderResponseDTO> getOrdersByUserId(@PathVariable Integer userId) {
        return orderQueryService.getProductsByUserId(userId);
    }


}
