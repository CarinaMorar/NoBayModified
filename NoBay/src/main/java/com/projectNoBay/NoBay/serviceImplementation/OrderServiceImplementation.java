package com.projectNoBay.NoBay.serviceImplementation;

import com.projectNoBay.NoBay.model.BeautyProducts;
import com.projectNoBay.NoBay.model.Orders;
import com.projectNoBay.NoBay.model.User;
import com.projectNoBay.NoBay.repository.OrderRepository;
import com.projectNoBay.NoBay.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImplementation implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void OrderServiceImplementation(OrderRepository orderRepository){
        this.orderRepository=orderRepository;
    }
    @Override
    public List<Orders> findAllOrders(){
        return (List<Orders>) orderRepository.findAll();
    }
    @Override
    public Optional<Orders> findOrderById(Long id){
        return orderRepository.findById(id);
    }
    @Override
    public Long deleteOrderById(Long id){
        orderRepository.deleteById(id);
        return id;
    }
    @Override
    public Orders deleteOrder(Orders orders){
        orderRepository.delete(orders);
        return orders;
    }
    @Override
    public Orders createOrder(Orders orders){
        return orderRepository.save(orders);
    }
    @Override
    public Orders updateOrderPrice(Orders orders, float price){
        Orders existingOrdersPrice = orderRepository.findById(orders.getId()).orElse(null);
        existingOrdersPrice.setPrice(price);
        return orderRepository.save(existingOrdersPrice);
    }
    @Override
    public Orders updateOrderQuantity(Orders orders, int quantity){
        Orders existingOrdersQuantity = orderRepository.findById(orders.getId()).orElse(null);
        existingOrdersQuantity.setQuantity(quantity);
        return orderRepository.save(existingOrdersQuantity);
    }

    @Override
    public Orders updateOrderBeautyProductsCart (Orders orders, List<BeautyProducts> beautyProductsCart){
        Orders existingOrders = orderRepository.findById(orders.getId()).orElse(null);
        existingOrders.setBeautyProductsCart(beautyProductsCart);
        return orderRepository.save(existingOrders);
    }
    @Override
    public Orders updateUser(Orders orders, User user){
        Orders existingClientOrders = orderRepository.findById(orders.getId()).orElse(null);
        existingClientOrders.setUser(user);
        return orderRepository.save(existingClientOrders);
    }
/*    @Override
    public Orders updateOrder(Orders Orders){
        Orders existingOrder = orderRepository.findById(Orders.getId()).orElse(null);
        existingOrder.setPrice(Orders.getPrice());
        existingOrder.setQuantity(Orders.getQuantity());
        existingOrder.setBeautyProductsCart(Orders.getBeautyProductsCart());
        existingOrder.setClientOrders(Orders.getClientOrders());
        return orderRepository.save(existingOrder);
    }*/
}
