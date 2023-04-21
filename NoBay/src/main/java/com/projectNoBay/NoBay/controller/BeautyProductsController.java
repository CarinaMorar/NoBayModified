package com.projectNoBay.NoBay.controller;

import com.projectNoBay.NoBay.DTO.BeautyProductsDTO;
import com.projectNoBay.NoBay.model.BeautyProducts;
import com.projectNoBay.NoBay.service.BeautyProductsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/beautyProducts")
public class BeautyProductsController {
    @Autowired
    private BeautyProductsService beautyProductsService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/findAll")
    public List<BeautyProductsDTO> findAllBeautyProducts(){
        List<BeautyProducts> beautyProducts1 = beautyProductsService.findAllBeautyProducts();
        List<BeautyProductsDTO> beautyProductsDTO = beautyProducts1.stream().map(beautyProducts ->
                modelMapper.map(beautyProducts,BeautyProductsDTO.class)).collect(Collectors.toList());
        return beautyProductsDTO;
    }

    @GetMapping("/findById{id}")
    public ResponseEntity findBeautyProductsById(@PathVariable Long id){
        Optional<BeautyProducts> beautyProducts = beautyProductsService.findBeautyProductsById(id);
        BeautyProductsDTO beautyProductsDTO = modelMapper.map(beautyProducts, BeautyProductsDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(beautyProductsDTO);
    }

    @GetMapping("/findAllByBrand{brand}")
    public ResponseEntity findBeautyProducstByBrand(@PathVariable String brand){
        List<BeautyProducts> beautyProducts = beautyProductsService.findBeautyProductsByBrand(brand);
        List<BeautyProductsDTO> beautyProductsDTO = beautyProducts.stream().map(beautyProducts1 ->
                modelMapper.map(beautyProducts1, BeautyProductsDTO.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(beautyProductsDTO);
    }

    @GetMapping("/findAllByName{name}")
    public ResponseEntity findBeautyProductsByName(@PathVariable String name){
        List<BeautyProducts> beautyProducts = beautyProductsService.findBeautyProductsByNameProduct(name);
        List<BeautyProductsDTO> beautyProductsDTO = beautyProducts.stream().map(beautyProducts1 ->
                modelMapper.map(beautyProducts, BeautyProductsDTO.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(beautyProductsDTO);
    }

    @DeleteMapping("/delete")
    public BeautyProductsDTO deleteBeautyProducts(@RequestBody BeautyProducts beautyProducts){
        BeautyProducts beautyProducts1 = beautyProductsService.deleteBeautyProducts(beautyProducts);
        BeautyProductsDTO beautyProductsDTO = modelMapper.map(beautyProducts1, BeautyProductsDTO.class);

        return beautyProductsDTO;
    }

    @DeleteMapping("/deleteById{id}")
    public BeautyProductsDTO deleteBeautyProductsById(@PathVariable Long id){
        Optional<BeautyProducts> beautyProducts1 = beautyProductsService.findBeautyProductsById(beautyProductsService.deleteBeautyProductsById(id));
        BeautyProductsDTO beautyProductsDTO = modelMapper.map(beautyProducts1, BeautyProductsDTO.class);

        return beautyProductsDTO;
    }

    @PutMapping("/updatePrice{price}")
    public BeautyProductsDTO updateBeautyProductsPrice(@RequestBody BeautyProducts beautyProducts, @PathVariable Float price){
        BeautyProducts beautyProducts1 = beautyProductsService.updateBeautyProductsPrice(beautyProducts, price);
        BeautyProductsDTO beautyProductsDTO = modelMapper.map(beautyProducts1, BeautyProductsDTO.class);
        return beautyProductsDTO;
    }

    @PutMapping("/updateQuantity{quantity}")
    public BeautyProductsDTO updateBeautyProductQuantity(@RequestBody BeautyProducts beautyProducts, @PathVariable int quantity){
        BeautyProducts beautyProducts1 = beautyProductsService.updateBeautyProductsQuantity(beautyProducts, quantity);
        BeautyProductsDTO beautyProductsDTO = modelMapper.map(beautyProducts1, BeautyProductsDTO.class);
        return beautyProductsDTO;
    }

    @PutMapping("/create")
    public BeautyProductsDTO createBeautyProducts(@RequestBody BeautyProducts beautyProducts) {
            BeautyProducts beautyProducts1 = beautyProductsService.createBeautyProducts(beautyProducts);
            BeautyProductsDTO beautyProductsDTO = modelMapper.map(beautyProducts1, BeautyProductsDTO.class);
            return beautyProductsDTO;
    }
}
