package com.common.order_service.dto;

public record OrderCreatedEvent(
        String orderId,
        String userId,
        String productName,
        double amount
) {}
