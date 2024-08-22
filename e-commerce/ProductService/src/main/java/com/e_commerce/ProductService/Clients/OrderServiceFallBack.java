package com.e_commerce.ProductService.Clients;

import org.springframework.stereotype.Component;

@Component
public class OrderServiceFallBack implements OrderServiceClient {
    @Override
    public String getOrderStatus(Long orderId) {
        return "Order Status Unavailable";
    }
}
