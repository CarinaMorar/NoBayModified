package com.projectNoBay.NoBay.serviceImplementation;

import com.projectNoBay.NoBay.model.BeautyProducts;
import com.projectNoBay.NoBay.repository.BeautyProductsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BeautyProductsServiceImplementationTest {
    @Mock
    private BeautyProductsRepository beautyProductsRepository;
    @InjectMocks
    private BeautyProductsServiceImplementation beautyProductsService;

    @Test
    @DisplayName("should update the price of the beauty product when the product exists")
    void updateBeautyProductsPriceWhenProductExists() {
        BeautyProducts beautyProducts = new BeautyProducts();
        beautyProducts.setId(1L);
        beautyProducts.setNameProduct("Lipstick");
        beautyProducts.setBrand("MAC");
        beautyProducts.setPrice(100);
        when(beautyProductsRepository.findById(1L)).thenReturn(Optional.of(beautyProducts));
        when(beautyProductsRepository.save(beautyProducts)).thenReturn(beautyProducts);
        BeautyProducts updatedBeautyProducts =
                beautyProductsService.updateBeautyProductsPrice(beautyProducts, 200);
        assertThat(updatedBeautyProducts.getPrice()).isEqualTo(200);
        verify(beautyProductsRepository, times(1)).findById(1L);
        verify(beautyProductsRepository, times(1)).save(beautyProducts);
    }

    @Test
    @DisplayName("should update the brand of the beauty product when the product exists")
    void updateBeautyProductsBrandWhenProductExists() {
        BeautyProducts beautyProducts = new BeautyProducts();
        beautyProducts.setId(1L);
        beautyProducts.setBrand("L'oreal");
        when(beautyProductsRepository.findById(1L)).thenReturn(Optional.of(beautyProducts));
        when(beautyProductsRepository.save(beautyProducts)).thenReturn(beautyProducts);
        BeautyProducts updatedBeautyProducts =
                beautyProductsService.updateBeautyProductsBrand(beautyProducts, "L'oreal");
        assertThat(updatedBeautyProducts.getBrand()).isEqualTo("L'oreal");
        verify(beautyProductsRepository, times(1)).findById(1L);
        verify(beautyProductsRepository, times(1)).save(beautyProducts);
    }

    @Test
    @DisplayName("should update the beauty product's name when a valid product is provided")
    void updateBeautyProductsNameWhenValidProductProvided() {
        BeautyProducts beautyProducts = new BeautyProducts();
        beautyProducts.setId(1L);
        beautyProducts.setNameProduct("Lipstick");
        beautyProducts.setBrand("MAC");
        beautyProducts.setPrice(100);
        when(beautyProductsRepository.findById(1L)).thenReturn(Optional.of(beautyProducts));
        when(beautyProductsRepository.save(beautyProducts)).thenReturn(beautyProducts);
        BeautyProducts updatedBeautyProducts =
                beautyProductsService.updateBeautyProductsName(beautyProducts, "Lipstick");
        assertThat(updatedBeautyProducts.getNameProduct()).isEqualTo("Lipstick");
        verify(beautyProductsRepository, times(1)).findById(1L);
        verify(beautyProductsRepository, times(1)).save(beautyProducts);
    }

    @Test
    @DisplayName("should delete beauty product")
    void deleteBeautyProducts() {
        BeautyProducts beautyProducts = new BeautyProducts();
        beautyProductsService.deleteBeautyProducts(beautyProducts);
        verify(beautyProductsRepository, times(1)).delete(beautyProducts);
    }

    @Test
    @DisplayName("should delete beauty product by id")
    void deleteBeautyProductsById() {
        BeautyProducts beautyProducts = new BeautyProducts();
        beautyProducts.setId(1L);
        beautyProducts.setNameProduct("Lipstick");
        beautyProducts.setBrand("MAC");
        beautyProducts.setPrice(100);
        beautyProducts.setQuantity(10);
        beautyProductsService.deleteBeautyProductsById(1L);
        verify(beautyProductsRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("should create a new beauty product")
    void createBeautyProducts() {
        BeautyProducts beautyProducts = new BeautyProducts();
        beautyProducts.setId(1L);
        beautyProducts.setNameProduct("Lipstick");
        beautyProducts.setBrand("MAC");
        beautyProducts.setPrice(100);
        when(beautyProductsRepository.save(beautyProducts)).thenReturn(beautyProducts);
        BeautyProducts createdBeautyProducts =
                beautyProductsService.createBeautyProducts(beautyProducts);
        assertThat(createdBeautyProducts).isEqualTo(beautyProducts);
    }

    @Test
    @DisplayName("should return all beauty products")
    void findAllBeautyProducts() {
        List<BeautyProducts> beautyProducts = new ArrayList<>();
        beautyProducts.add(
                BeautyProducts.builder()
                        .id(1L)
                        .nameProduct("Lipstick")
                        .brand("MAC")
                        .quantity(1)
                        .price(100)
                        .build());
        beautyProducts.add(
                BeautyProducts.builder()
                        .id(2L)
                        .nameProduct("Lipstick")
                        .brand("MAC")
                        .quantity(1)
                        .price(100)
                        .build());
        when(beautyProductsRepository.findAll()).thenReturn(beautyProducts);
        List<BeautyProducts> result = beautyProductsService.findAllBeautyProducts();
        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(2);
        verify(beautyProductsRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("should return beauty product by id")
    void findBeautyProductsById() {
        BeautyProducts beautyProducts = new BeautyProducts();
        beautyProducts.setId(1L);
        beautyProducts.setNameProduct("Lipstick");
        beautyProducts.setBrand("MAC");
        beautyProducts.setPrice(100);
        when(beautyProductsRepository.findById(1L)).thenReturn(Optional.of(beautyProducts));
        Optional<BeautyProducts> foundBeautyProducts =
                beautyProductsService.findBeautyProductsById(1L);
        assertThat(foundBeautyProducts).isNotNull();
        assertThat(foundBeautyProducts.get().getId()).isEqualTo(1L);
        assertThat(foundBeautyProducts.get().getNameProduct()).isEqualTo("Lipstick");
        assertThat(foundBeautyProducts.get().getBrand()).isEqualTo("MAC");
        assertThat(foundBeautyProducts.get().getPrice()).isEqualTo(100);
    }
}