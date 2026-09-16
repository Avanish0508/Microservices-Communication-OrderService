package com.common.order_service.service;

import com.common.order_service.dto.OrderCreatedEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class NotificationClient {

    private final RestClient restClient;

    public NotificationClient(RestClient notificationRestClient) {
        this.restClient = notificationRestClient;
    }

    public void notifyUser(OrderCreatedEvent event) {
        restClient.post()
                .uri("/notifications")
                .body(event)
                .retrieve()                    // (3)
                .toBodilessEntity();           // blocks here until response arrives
    }
}
