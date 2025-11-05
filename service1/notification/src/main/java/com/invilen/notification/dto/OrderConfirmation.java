package com.invilen.notification.dto;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String reference,
        CustomerResponse customer,
        List<PurchaseResponse> purchasedProduct,
        BigDecimal totalAmount
) {
}
