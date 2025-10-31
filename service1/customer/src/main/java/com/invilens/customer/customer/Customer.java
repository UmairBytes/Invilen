package com.invilens.customer.customer;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@Data
@Builder
@Document
public class Customer {


    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private Address address;
}
