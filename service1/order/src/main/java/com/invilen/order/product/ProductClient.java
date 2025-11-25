package com.invilen.order.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductClient {
    @PostMapping
    public List<PurchaseResponse> getProductById (List<PurchaseRequest> requests);
}
