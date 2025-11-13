package com.invilen.notification.dto;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
