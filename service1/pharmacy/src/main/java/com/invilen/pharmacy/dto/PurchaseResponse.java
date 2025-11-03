package com.invilen.pharmacy.dto;

import java.math.BigDecimal;

public record PurchaseResponse(
        Integer Id,
        String name,
        String description,
        BigDecimal price,
        double availableQuantity
) {
}
