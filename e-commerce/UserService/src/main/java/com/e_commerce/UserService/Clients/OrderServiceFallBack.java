package com.e_commerce.UserService.Clients;

import org.springframework.stereotype.Component;

@Component
public class OrderServiceFallBack implements OrderServiceClient {

    @Override
    public String getOrderStatus(Long orderId) {
        // Return a default message indicating the service is unavailable
        return "Order status is unavailable";
    }
}
