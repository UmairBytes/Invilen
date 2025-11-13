package com.invilen.order.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "pharmacy-service",
        url = "${application.config.pharmacy-url}"
)
public interface ProductClient {
    @PostMapping
    public List<PurchaseResponse> getPurchaseProducts (@RequestBody List<PurchaseRequest> products);
}
