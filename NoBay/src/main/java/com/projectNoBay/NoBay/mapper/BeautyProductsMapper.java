package com.projectNoBay.NoBay.mapper;


import com.projectNoBay.NoBay.DTO.BeautyProductsDTO;
import com.projectNoBay.NoBay.model.BeautyProducts;
import org.springframework.stereotype.Component;

@Component
public class BeautyProductsMapper {
    public static BeautyProductsDTO mapModelToDto(BeautyProducts beautyProducts){
        BeautyProductsDTO beautyProductsDTO = new BeautyProductsDTO();
        beautyProductsDTO.setId(beautyProducts.getId());
        beautyProductsDTO.setNameProduct(beautyProducts.getNameProduct());
        beautyProductsDTO.setBrand(beautyProducts.getBrand());
        beautyProductsDTO.setQuantity(beautyProducts.getQuantity());
        beautyProductsDTO.setPrice(beautyProducts.getPrice());

        return beautyProductsDTO;
    }

    public static BeautyProducts mapDtoToModel(BeautyProductsDTO beautyProductsDTO){
        BeautyProducts beautyProducts = new BeautyProducts();
        beautyProducts.setId(beautyProductsDTO.getId());
        beautyProducts.setNameProduct(beautyProductsDTO.getNameProduct());
        beautyProducts.setBrand(beautyProductsDTO.getBrand());
        beautyProducts.setQuantity(beautyProductsDTO.getQuantity());
        beautyProducts.setPrice(beautyProductsDTO.getPrice());

        return beautyProducts;
    }
}
