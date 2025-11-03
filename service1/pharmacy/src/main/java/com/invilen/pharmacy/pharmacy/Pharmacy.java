package com.invilen.pharmacy.pharmacy;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "medicine")
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String brand;
    private String dosage;
    private Double availableQuantity;
    private BigDecimal price;
    private LocalDate expiry_date;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}

