package com.common.order_service.controller;


import com.common.order_service.grpc.NotificationGrpcClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class GrpcTestController {

    private final NotificationGrpcClient notificationGrpcClient;

    public GrpcTestController(NotificationGrpcClient notificationGrpcClient) {
        this.notificationGrpcClient = notificationGrpcClient;
    }

    @PostMapping("/notification")
    public String testNotification() {

        notificationGrpcClient.notifyUser(
                "ORDER-10000000000000001",
                "USER-101",
                "Laptop",
                75000.0
        );

        return "gRPC notification request sent";
    }
}
