package com.projectNoBay.NoBay.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BeautyProductsDTO {
    private Long id;
    private String nameProduct;
    private String brand;
    private float quantity;
    private float price;
}
