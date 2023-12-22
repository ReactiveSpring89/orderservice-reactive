package com.app.orderservice.service.impl;

import com.app.orderservice.models.dto.PurchaseOrderRequestDTO;
import com.app.orderservice.models.dto.PurchaseOrderResponseDTO;
import com.app.orderservice.models.dto.RequestContext;
import com.app.orderservice.repository.PurchaseOrderRepository;
import com.app.orderservice.service.OrderFulfillmentService;
import com.app.orderservice.serviceclients.ProductClient;
import com.app.orderservice.serviceclients.UserClient;
import com.app.orderservice.util.EntityDTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
public class OrderFulfillmentServiceImpl implements OrderFulfillmentService {
    @Autowired
    private ProductClient productClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public Mono<PurchaseOrderResponseDTO> processOrder(Mono<PurchaseOrderRequestDTO> purchaseOrderRequestDTOMono) {
        return purchaseOrderRequestDTOMono.map(RequestContext::new)
                .flatMap(this::getProductResponse)
                .doOnNext(EntityDTOUtil::setTransactionRequestDTO)
                .flatMap(this::getUserResponse)
                .map(EntityDTOUtil::getPurchaseOrder)
                .map(purchaseOrderRepository::save)
                .map(EntityDTOUtil::getPurchaseOrderResponse)
                .subscribeOn(Schedulers.boundedElastic());
    }

    private Mono<RequestContext> getProductResponse(RequestContext requestContext) {
        return productClient.getProductById(requestContext.getPurchaseOrderRequestDTO().getProductId())
                .doOnNext(requestContext::setProductDTO)
                .retryWhen(Retry.fixedDelay(1, Duration.ofSeconds(1)))
                .thenReturn(requestContext);
    }

    private Mono<RequestContext> getUserResponse(RequestContext requestContext) {
        return userClient.authorizeTransaction(requestContext.getTransactionRequestDTO())
                .doOnNext(requestContext::setTransactionResponseDTO)
                .thenReturn(requestContext);
    }
}
