package com.binary.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private  Long custId;

    private String custName;

    private Long custContactNumber;

    private String custEmailId;

    private double custAccountBalance;

}
