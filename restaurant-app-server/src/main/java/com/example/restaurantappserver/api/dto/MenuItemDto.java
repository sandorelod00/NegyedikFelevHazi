package com.example.restaurantappserver.api.dto;

import com.example.restaurantappserver.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemDto {
    private Long id;
    private Double profitPercent;
    private Long recipeId;
    private Long itemId;

    private ItemDto itemDto;
    private RecipeDto recipeDto;

    private Double price;
}
