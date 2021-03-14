package com.example.restaurantappserver.api.dto;

import com.example.restaurantappserver.entity.MenuItem;
import com.example.restaurantappserver.enums.OrderStatus;
import com.example.restaurantappserver.enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private OrderType orderType;
    private OrderStatus orderStatus;

    private Double price;
    private Long customerId;
    private UserDto customer;


    private Long tableId;
    private TableDto tableDto;

    private List<Long> menuItems;
    private List<MenuItemDto> menuItemsDto;

}
