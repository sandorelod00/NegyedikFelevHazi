package com.example.restaurantappserver.api.dto;

import com.example.restaurantappserver.entity.Ingredient;
import com.example.restaurantappserver.entity.Item;
import com.example.restaurantappserver.entity.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDto {
    private Long id;
    private Long recipeId;
    private Double quantityNeeded;
    private Long itemId;
    private String itemName;
    private Double price;
    private Boolean isAvailable;
}
