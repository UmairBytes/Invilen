package com.invilen.pharmacy.pharmacy;


import com.invilen.pharmacy.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    }
}
