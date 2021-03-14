package com.example.restaurantappserver.api.dto;

import com.example.restaurantappserver.entity.Ingredient;
import com.example.restaurantappserver.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {

    private Long id;
    private String name;
    private List<IngredientDto> ingredients;
    private Double price;
    private Boolean isAvailable;
}
