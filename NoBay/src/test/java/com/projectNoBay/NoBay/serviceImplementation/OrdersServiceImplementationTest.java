package com.projectNoBay.NoBay.serviceImplementation;

import com.projectNoBay.NoBay.model.BeautyProducts;
import com.projectNoBay.NoBay.model.Orders;
import com.projectNoBay.NoBay.model.User;
import com.projectNoBay.NoBay.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
class OrdersServiceImplementationTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImplementation orderService;

    private Orders orders;
    private Long id;
    @BeforeEach
    void init() {
        initMocks(this);
        orders =new Orders();
        orders.setId(id);
    }

    @Test
    @DisplayName("should update the client orders when the orders and client are valid")
    void updateClientOrderWhenOrderAndClientAreValid() {
        Orders orders = Orders.builder().id(1L).price(100).quantity(1).build();
        User clientOrder =
                User.builder()
                        .id(1L)
                        .username("username")
                        .email("email")
                        .password("password")
                        .firstName("firstName")
                        .lastName("lastName")
                        .build();
        when(orderRepository.findById(orders.getId())).thenReturn(Optional.of(orders));
        when(orderRepository.save(orders)).thenReturn(orders);

        Orders updatedOrders = orderService.updateUser(orders, clientOrder);

        assertThat(updatedOrders).isNotNull();
        assertThat(updatedOrders.getUser()).isEqualTo(clientOrder);
        verify(orderRepository, times(1)).findById(orders.getId());
        verify(orderRepository, times(1)).save(orders);
    }

    @Test
    @DisplayName("should update the beauty products cart of the given orders")
    void updateOrderBeautyProductsCart() {
        Orders orders = Orders.builder().id(1L).build();
        List<BeautyProducts> beautyProductsCart =
                Arrays.asList(BeautyProducts.builder().id(1L).build());
        when(orderRepository.findById(orders.getId())).thenReturn(Optional.of(orders));
        when(orderRepository.save(orders)).thenReturn(orders);

        Orders updatedOrders = orderService.updateOrderBeautyProductsCart(orders, beautyProductsCart);

        assertThat(updatedOrders.getBeautyProductsCart()).isEqualTo(beautyProductsCart);
        verify(orderRepository, times(1)).findById(orders.getId());
        verify(orderRepository, times(1)).save(orders);
    }

    @Test
    @DisplayName("should update the orders quantity when a valid orders is provided")
    void updateOrderQuantityWhenValidOrderIsProvided() {
        Orders orders = Orders.builder().id(1L).quantity(2).build();
        when(orderRepository.findById(1L)).thenReturn(Optional.of(orders));
        when(orderRepository.save(orders)).thenReturn(orders);

        Orders updatedOrders = orderService.updateOrderQuantity(orders, 3);

        assertThat(updatedOrders.getQuantity()).isEqualTo(3);
        verify(orderRepository, times(1)).findById(1L);
        verify(orderRepository, times(1)).save(orders);
    }

    @Test
    @DisplayName("should update the orders price when a valid orders and price are provided")
    void updateOrderPriceWhenValidOrderAndPriceProvided() {
        Orders orders = new Orders();
        orders.setId(1L);
        orders.setPrice(100);
        orders.setQuantity(1);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(orders));
        when(orderRepository.save(orders)).thenReturn(orders);

        Orders updatedOrders = orderService.updateOrderPrice(orders, 200);

        assertThat(updatedOrders.getPrice()).isEqualTo(200);
        verify(orderRepository, times(1)).findById(1L);
        verify(orderRepository, times(1)).save(orders);
    }

    @Test
    @DisplayName("should delete an orders")
    void deleteOrder() {
        Orders orders = Orders.builder().id(1L).build();
        orderService.deleteOrder(orders);
        verify(orderRepository, times(1)).delete(orders);
    }

    @Test
    @DisplayName("should create a new orders")
    void createOrder() {
        Orders orders = new Orders();
        when(orderRepository.save(orders)).thenReturn(orders);
        Orders createdOrders = orderService.createOrder(orders);
        assertThat(createdOrders).isEqualTo(orders);
    }

    @Test
    @DisplayName("should delete an orders by its ID")
    void deleteOrderById() {
        orderService.deleteOrderById(1L);
        verify(orderRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("should return an orders by its ID")
    void findOrderById() {
        Orders orders = Orders.builder().id(1L).build();
        when(orderRepository.findById(1L)).thenReturn(Optional.of(orders));
        Orders result = orderService.findOrderById(1L).get();
        assertThat(result).isEqualTo(orders);
    }

    @Test
    @DisplayName("should return all orders")
    void findAllOrders() {
        Orders orders1 = new Orders();
        Orders orders2 = new Orders();
        List<Orders> orders = Arrays.asList(orders1, orders2);
        when(orderRepository.findAll()).thenReturn(orders);
        List<Orders> result = orderService.findAllOrders();
        assertThat(result).isEqualTo(orders);
    }
}