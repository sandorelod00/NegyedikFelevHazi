package com.example.restaurantappserver.api.service;

import com.example.restaurantappserver.api.dto.OrderDto;

import java.util.List;

public interface OrderService {


    void AddNewOrder(OrderDto orderDto);

    List<OrderDto> getAll();

    void deleteOrder(Long id);

    List<OrderDto> getMyOrders(Long id);

    void closeOrderById(Long id);
}
