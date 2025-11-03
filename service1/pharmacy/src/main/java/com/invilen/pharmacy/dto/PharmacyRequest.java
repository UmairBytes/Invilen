package com.invilen.pharmacy.dto;

import com.invilen.pharmacy.pharmacy.Category;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public record PharmacyRequest(
        String description,
        @NotNull(message = "name cannot be empty")
        String name,
        @NotNull(message = "quantity cannot be empty")
        Double Quantity,
        String brand,
        String dosage,
        @NotNull(message = "Date cannot be empty")
        LocalDate expiry_date,
        @NotNull(message = "price cannot be empty")
        BigDecimal price,
        @NotNull(message = "category cannot be empty")
        Category category
) {
}
