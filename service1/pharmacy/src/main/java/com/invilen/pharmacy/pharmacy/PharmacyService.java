package com.invilen.pharmacy.pharmacy;


import com.invilen.pharmacy.dto.*;
import com.invilen.pharmacy.exception.ProductPurchaseException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PharmacyService {
    private final PharmacyRepository pharmacyRepository;
    private final PharmacyMapper mapper;

    public PharmacyResponse findById(Integer id) {
        return mapper.toRespone(pharmacyRepository.findById(id).orElseThrow());
    }

    public Integer productCreated(PharmacyRequest request) {
        var product = pharmacyRepository.save(mapper.toEntity(request));
        return product.getId();
    }

    public Page<PharmacyResponse> findAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return pharmacyRepository.findAll(pageable)
                .map(mapper::toResponse);

    }

    public List<PurchaseResponse> purchaseProduct(List<PurchaseRequest> purchaseRequest) {
        var productIds = purchaseRequest
                .stream()
                .map(PurchaseRequest::Id)
                .toList();
        var storedProduct = pharmacyRepository.findAllById(productIds)
                .stream()
                .sorted(Comparator.comparing(Pharmacy::getId))
                .toList();
        if(productIds.size() != storedProduct.size()){
            throw new ProductPurchaseException("One and More products does not exist");
        }

        var storedRequest = purchaseRequest
                .stream()
                .sorted(Comparator.comparing(PurchaseRequest::Id))
                .toList();
        var purchasedProducts = new ArrayList<PurchaseResponse>();
        for(int i=0; i < storedProduct.size(); i++){
            var product = storedProduct.get(i);
            var productRequest = storedProduct.get(i);
            if(product.getAvailableQuantity() < productRequest.getAvailableQuantity()){
                throw new  ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productRequest.getId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.getAvailableQuantity();
            product.setAvailableQuantity(newAvailableQuantity);
            pharmacyRepository.save(product);
            purchasedProducts.add(mapper.toPurchaseResponse(product,productRequest.getAvailableQuantity()));

        }
        return purchasedProducts;
    }
}
