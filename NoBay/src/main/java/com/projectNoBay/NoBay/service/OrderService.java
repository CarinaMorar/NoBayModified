package com.projectNoBay.NoBay.service;

import com.projectNoBay.NoBay.model.BeautyProducts;
import com.projectNoBay.NoBay.model.Orders;
import com.projectNoBay.NoBay.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface OrderService {
    List<Orders> findAllOrders();

    Optional<Orders> findOrderById(Long id);

    Long deleteOrderById(Long id);

    Orders deleteOrder(Orders orders);

    Orders createOrder(Orders orders);

    Orders updateOrderPrice(Orders orders, float price);

    Orders updateOrderQuantity(Orders orders, int quantity);

    Orders updateOrderBeautyProductsCart(Orders orders, List<BeautyProducts> beautyProductsCart);

    Orders updateUser(Orders orders, User user);

    //Orders updateClientOrder(Orders Orders, User clientOrders);

    //Orders updateOrder(Orders Orders);
}
