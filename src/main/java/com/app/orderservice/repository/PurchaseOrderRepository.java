package com.app.orderservice.repository;

import com.app.orderservice.models.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
    List<PurchaseOrder> findByUserId(Integer userId);
}
