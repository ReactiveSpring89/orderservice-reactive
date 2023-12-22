package com.app.orderservice.service.impl;

import com.app.orderservice.models.dto.PurchaseOrderResponseDTO;
import com.app.orderservice.models.entity.PurchaseOrder;
import com.app.orderservice.repository.PurchaseOrderRepository;
import com.app.orderservice.service.OrderQueryService;
import com.app.orderservice.util.EntityDTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
public class OrderQueryServiceImpl implements OrderQueryService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public Flux<PurchaseOrderResponseDTO> getProductsByUserId(Integer userId) {
       return Flux.fromStream(() -> purchaseOrderRepository.findByUserId(userId).stream())
               .map(EntityDTOUtil::getPurchaseOrderResponse)
               .subscribeOn(Schedulers.boundedElastic());
    }
}
