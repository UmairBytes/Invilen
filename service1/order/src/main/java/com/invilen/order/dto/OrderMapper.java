package com.invilen.order.dto;

import com.invilen.order.order.Order;
import com.invilen.order.orderItem.OrderItems;
import com.invilen.order.product.PurchaseRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
//              order.getPaymentMethod(),
              order.getCustomerId()
        );
    }

    public Order toOrder(@Valid OrderRequest request) {
        Order order = new Order();
        order.setReference(request.reference());
        order.setTotalAmount(request.amount());
        order.setCustomerId(request.customerId());
        return order;
    }

    public OrderItems toOrderItem(@Valid PurchaseRequest request, Order order) {
        OrderItems orderItems = new OrderItems();
        orderItems.setOrder(order);
        orderItems.setQuantity(request.quantity());
        orderItems.setProductId(request.productId());
        return orderItems;

    }
}
