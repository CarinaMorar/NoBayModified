package com.projectNoBay.NoBay.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@Entity
@RequiredArgsConstructor
@AllArgsConstructor

public class BeautyProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameProduct;
    private String brand;
    private float quantity;
    private float price;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Orders clientOrders;

}
