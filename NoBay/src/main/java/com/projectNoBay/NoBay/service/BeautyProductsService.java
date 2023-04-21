package com.projectNoBay.NoBay.service;

import com.projectNoBay.NoBay.DTO.BeautyProductsDTO;
import com.projectNoBay.NoBay.model.BeautyProducts;
import com.projectNoBay.NoBay.model.Orders;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public interface BeautyProductsService{
    List<BeautyProducts> findAllBeautyProducts();

    Optional<BeautyProducts> findBeautyProductsById(Long id);

    List<BeautyProducts> findBeautyProductsByBrand(String brand);

    List<BeautyProducts> findBeautyProductsByNameProduct(String name);

    Long deleteBeautyProductsById(Long id);

    BeautyProducts deleteBeautyProducts(BeautyProducts beautyProducts);

    BeautyProducts createBeautyProducts(BeautyProducts beautyProducts);

    BeautyProducts updateBeautyProductsName(BeautyProducts beautyProducts, String name);

    BeautyProducts updateBeautyProductsBrand(BeautyProducts beautyProducts, String brand);

    BeautyProducts updateBeautyProductsPrice(BeautyProducts beautyProducts, float price);

    BeautyProducts updateBeautyProductsQuantity(BeautyProducts beautyProducts, int quantity);

    BeautyProducts updateBeautyProductsClientOrder(BeautyProducts beautyProducts, Orders clientOrders);

    //BeautyProducts updateBeautyProducts(BeautyProducts beautyProducts);
}
