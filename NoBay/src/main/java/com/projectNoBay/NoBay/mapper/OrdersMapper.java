package com.projectNoBay.NoBay.mapper;

import com.projectNoBay.NoBay.DTO.OrdersDTO;
import com.projectNoBay.NoBay.model.Orders;
import org.springframework.stereotype.Component;

@Component
public class OrdersMapper {
    public static OrdersDTO mapModelToDto(Orders orders){
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setId(orders.getId());
        ordersDTO.setQuantity(orders.getQuantity());
        ordersDTO.setPrice(orders.getPrice());

        return ordersDTO;
    }

    public static Orders mapDtoToModel(OrdersDTO ordersDTO){
        Orders orders = new Orders();
        orders.setId(ordersDTO.getId());
        orders.setQuantity(ordersDTO.getQuantity());
        orders.setPrice(ordersDTO.getPrice());

        return orders;
    }
}
