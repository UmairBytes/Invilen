package com.invilen.pharmacy.dto;

import com.invilen.pharmacy.pharmacy.Pharmacy;
import org.springframework.stereotype.Service;

@Service
public class PharmacyMapper {
    public Pharmacy toEntity(PharmacyRequest request) {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setName(request.name());
        pharmacy.setDescription(request.description());
        pharmacy.setAvailableQuantity(request.Quantity());
        pharmacy.setDosage(request.dosage());
        pharmacy.setPrice(request.price());
        pharmacy.setExpiry_date(request.expiry_date());
        pharmacy.setBrand(request.brand());
        return pharmacy;
    }

    public PharmacyResponse toResponse(Pharmacy pharmacy) {
        return new PharmacyResponse(
                pharmacy.getId(),
                pharmacy.getDescription(),
                pharmacy.getName(),
                pharmacy.getDosage(),
                pharmacy.getBrand(),
                pharmacy.getAvailableQuantity(),
                pharmacy.getPrice(),
                pharmacy.getExpiry_date(),
                pharmacy.getCategory()

        );
    }

    public PharmacyResponse toRespone(Pharmacy pharmacy) {
        return new PharmacyResponse(
                pharmacy.getId(),
                pharmacy.getDescription(),
                pharmacy.getName(),
                pharmacy.getDosage(),
                pharmacy.getBrand(),
                pharmacy.getAvailableQuantity(),
                pharmacy.getPrice(),
                pharmacy.getExpiry_date(),
                pharmacy.getCategory()
        );
    }

    public PurchaseResponse toPurchaseResponse(Pharmacy product, Double availableQuantity) {
        return new PurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                availableQuantity
        );
    }
}
