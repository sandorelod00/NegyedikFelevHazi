package com.example.restaurantappserver.api.dto;

import com.example.restaurantappserver.enums.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private Long id;
    private String name;
    private Double totalQuantity;
    private Double price;
    private ItemType itemType;
    private Double totalPrice;
}
