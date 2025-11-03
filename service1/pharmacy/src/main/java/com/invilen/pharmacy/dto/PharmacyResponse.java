package com.invilen.pharmacy.dto;

import com.invilen.pharmacy.pharmacy.Category;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public record PharmacyResponse(
        Integer id,
        String description,
        String name,
        String dosage,
        String brand,
        Double Quantity,
        BigDecimal price,
        LocalDate expiry_date,
        Category category
) {
}
