package com.common.order_service.dto;


public record OrderRequest(
        String userId,
        String productName,
        double amount
) {}
