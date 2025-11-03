package com.invilen.order.dto;

import com.invilen.order.order.Order;

public class OrderMapper {
    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount()
//              order.getPaymentMethod(),
//              order.getCustomerId()
        );
    }
}
