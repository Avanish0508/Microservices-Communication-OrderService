package com.common.order_service.controller;

import com.common.order_service.dto.OrderCreatedEvent;
import com.common.order_service.dto.OrderRequest;
import com.common.order_service.service.NotificationClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

    @RestController
    @RequestMapping("/orders")
    public class OrderController {

        private final NotificationClient notificationClient;

        public OrderController(NotificationClient notificationClient) {
            this.notificationClient = notificationClient;
        }

        @PostMapping
        public ResponseEntity<String> createOrder(@RequestBody OrderRequest request) {
            String orderId = UUID.randomUUID().toString();

            // ... persist order to DB here in a real app ...

            OrderCreatedEvent event = new OrderCreatedEvent(
                    orderId, request.userId(), request.productName(), request.amount());

            notificationClient.notifyUser(event);   // (4) BLOCKS until Notification Service replies

            return ResponseEntity.ok("Order created: " + orderId);
        }
    }
