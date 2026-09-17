package com.common.order_service.grpc;


import com.common.grpc.NotificationServiceGrpc;
import com.common.grpc.NotifyRequest;
import com.common.grpc.NotifyResponse;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class NotificationGrpcClient {

    @GrpcClient("notification-service")
    private NotificationServiceGrpc.NotificationServiceBlockingStub stub;

    public void notifyUser(
            String orderId,
            String userId,
            String productName,
            double amount) {

        NotifyRequest request = NotifyRequest.newBuilder()
                .setOrderId(orderId)
                .setUserId(userId)
                .setProductName(productName)
                .setAmount(amount)
                .build();

        NotifyResponse response = stub.notify(request);

        System.out.println("gRPC response: " + response.getMessage());
    }
}