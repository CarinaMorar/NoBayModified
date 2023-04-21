package com.projectNoBay.NoBay.serviceImplementation;

import com.projectNoBay.NoBay.model.BeautyProducts;
import com.projectNoBay.NoBay.model.Orders;
import com.projectNoBay.NoBay.repository.BeautyProductsRepository;
import com.projectNoBay.NoBay.service.BeautyProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class BeautyProductsServiceImplementation implements BeautyProductsService {
    @Autowired
    private BeautyProductsRepository beautyProductsRepository;
    public void BeautyProductsServiceImplementation(BeautyProductsRepository beautyProductsRepository){
        this.beautyProductsRepository = beautyProductsRepository;
    }
    @Override
    public List<BeautyProducts> findAllBeautyProducts(){
        return (List<BeautyProducts>)beautyProductsRepository.findAll();
    }
    @Override
    public Optional<BeautyProducts> findBeautyProductsById(Long id ){
        return beautyProductsRepository.findById(id);
    }

    @Override
    public List<BeautyProducts> findBeautyProductsByBrand(String brand){return beautyProductsRepository.findBeautyProductsByBrand(brand);}
    @Override
    public List<BeautyProducts> findBeautyProductsByNameProduct(String name){return beautyProductsRepository.findBeautyProductsByNameProduct(name);}




    @Override
    public Long deleteBeautyProductsById(Long id){
        beautyProductsRepository.deleteById(id);
        return id;
    }
    @Override
    public BeautyProducts deleteBeautyProducts(BeautyProducts beautyProducts){
        beautyProductsRepository.delete(beautyProducts);
        return beautyProducts;
    }
    @Override
    public BeautyProducts createBeautyProducts(BeautyProducts beautyProducts){
        return beautyProductsRepository.save(beautyProducts);
    }
    @Override
    public BeautyProducts updateBeautyProductsName(BeautyProducts beautyProducts,String name){
        BeautyProducts existingBeautyProducts = beautyProductsRepository.findById(beautyProducts.getId()).orElse(null);
        existingBeautyProducts.setNameProduct(name);
        return beautyProductsRepository.save(existingBeautyProducts);
    }
    @Override
    public BeautyProducts updateBeautyProductsBrand(BeautyProducts beautyProducts,String brand){
        BeautyProducts existingBeautyProducts = beautyProductsRepository.findById(beautyProducts.getId()).orElse(null);
        existingBeautyProducts.setBrand(brand);
        return beautyProductsRepository.save(existingBeautyProducts);
    }
    @Override
    public BeautyProducts updateBeautyProductsPrice(BeautyProducts beautyProducts,float price){
        BeautyProducts existingBeautyProducts = beautyProductsRepository.findById(beautyProducts.getId()).orElse(null);
        existingBeautyProducts.setPrice(price);
        return beautyProductsRepository.save(existingBeautyProducts);
    }

    @Override
    public BeautyProducts updateBeautyProductsQuantity(BeautyProducts beautyProducts,int quantity){
        BeautyProducts existingBeautyProducts = beautyProductsRepository.findById(beautyProducts.getId()).orElse(null);
        existingBeautyProducts.setQuantity(quantity);
        return beautyProductsRepository.save(existingBeautyProducts);
    }

    @Override
    public BeautyProducts updateBeautyProductsClientOrder(BeautyProducts beautyProducts, Orders clientOrders){
        BeautyProducts existingBeautyProducts = beautyProductsRepository.findById(beautyProducts.getId()).orElse(null);
        existingBeautyProducts.setClientOrders(clientOrders);
        return beautyProductsRepository.save(existingBeautyProducts);
    }

    /*@Override
    public BeautyProducts updateBeautyProducts(BeautyProducts beautyProducts){
        BeautyProducts existingBeautyProducts = beautyProductsRepository.findById(beautyProducts.getId()).orElse(null);
        existingBeautyProducts.setNameProduct(beautyProducts.getNameProduct());
        existingBeautyProducts.setBrand(beautyProducts.getBrand());
        existingBeautyProducts.setPrice(beautyProducts.getPrice());
        existingBeautyProducts.setId(beautyProducts.getId());
        return beautyProductsRepository.save(existingBeautyProducts);
    }*/
}
