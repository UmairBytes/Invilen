package com.invilen.order.product;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(
        name = "pharmacy-service",
        url = "${application.config.pharmacy-url}"
)
public interface ProductClient {

    public List<PurchaseResponse> getPurchaseProducts (List<PurchaseRequest> products);
}
