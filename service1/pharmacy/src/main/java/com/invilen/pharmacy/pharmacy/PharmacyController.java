package com.invilen.pharmacy.pharmacy;

import com.invilen.pharmacy.dto.PharmacyRequest;
import com.invilen.pharmacy.dto.PharmacyResponse;
import com.invilen.pharmacy.dto.PurchaseRequest;
import com.invilen.pharmacy.dto.PurchaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pharmacy")
@RequiredArgsConstructor
public class PharmacyController {
     private final PharmacyService pharmacyService;

     @PostMapping
     public ResponseEntity<Integer> createProduct(@RequestBody @Valid PharmacyRequest request){
     return ResponseEntity.ok(pharmacyService.productCreated(request));
     }

     @GetMapping
     public Page<PharmacyResponse> GetAllProducts(
             @RequestParam(defaultValue = "0") int pageNumber,
             @RequestParam(defaultValue = "10") int pageSize
     ){
         return pharmacyService.findAll(pageNumber,pageSize);
     }

     @GetMapping("/{id}")
     public ResponseEntity<PharmacyResponse> findById(@PathVariable Integer id){
         return ResponseEntity.ok(pharmacyService.findById(id));
     }

     @PostMapping("/purchase")
     public List<PurchaseResponse> pharmacyPurchase(@RequestBody @Valid List<PurchaseRequest> purchaseRequest){
         return pharmacyService.purchaseProduct(purchaseRequest);
     }

}

