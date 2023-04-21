package com.projectNoBay.NoBay.repository;

import com.projectNoBay.NoBay.DTO.BeautyProductsDTO;
import com.projectNoBay.NoBay.model.BeautyProducts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeautyProductsRepository extends CrudRepository<BeautyProducts,Long> {
    BeautyProducts findFirstBeautyProductsById(Long id);

    BeautyProductsDTO findBeautyProductsById(Long id);

    List<BeautyProducts> findBeautyProductsByBrand(String brand);

    List<BeautyProducts> findBeautyProductsByNameProduct(String name);
}
