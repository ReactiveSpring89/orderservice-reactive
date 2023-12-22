package com.app.orderservice.util;

import com.app.orderservice.models.dto.PurchaseOrderResponseDTO;
import com.app.orderservice.models.dto.RequestContext;
import com.app.orderservice.models.dto.TransactionRequestDTO;
import com.app.orderservice.models.entity.PurchaseOrder;
import com.app.orderservice.models.enums.OrderStatus;
import com.app.orderservice.models.enums.TransactionStatus;
import org.springframework.beans.BeanUtils;

public class EntityDTOUtil {
    public static void setTransactionRequestDTO(RequestContext requestContext) {
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO();
        transactionRequestDTO.setUserId(requestContext.getPurchaseOrderRequestDTO().getUserId());
        transactionRequestDTO.setAmount(requestContext.getProductDTO().getPrice());
        requestContext.setTransactionRequestDTO(transactionRequestDTO);
    }

    public static PurchaseOrder getPurchaseOrder(RequestContext requestContext) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setUserId(requestContext.getTransactionRequestDTO().getUserId());
        purchaseOrder.setProductId(requestContext.getPurchaseOrderRequestDTO().getProductId());
        purchaseOrder.setAmount(requestContext.getProductDTO().getPrice());
        TransactionStatus status = requestContext.getTransactionResponseDTO().getStatus();
        OrderStatus orderStatus = status.equals(TransactionStatus.APPROVED) ? OrderStatus.COMPLETED: OrderStatus.FAILED;
        purchaseOrder.setStatus(orderStatus);
        return purchaseOrder;
    }

    public static PurchaseOrderResponseDTO getPurchaseOrderResponse(PurchaseOrder purchaseOrder) {
        PurchaseOrderResponseDTO purchaseOrderResponseDTO = new PurchaseOrderResponseDTO();
        BeanUtils.copyProperties(purchaseOrder, purchaseOrderResponseDTO);
        purchaseOrderResponseDTO.setOrderId(purchaseOrder.getId());
        return purchaseOrderResponseDTO;
    }
}
