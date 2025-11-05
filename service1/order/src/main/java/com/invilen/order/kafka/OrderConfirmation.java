package com.invilen.order.kafka;

import com.invilen.order.customer.CustomerResponse;
import com.invilen.order.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
         String reference,
         CustomerResponse customer,
         List<PurchaseResponse> purchasedProduct,
         BigDecimal totalAmount
) {
}
