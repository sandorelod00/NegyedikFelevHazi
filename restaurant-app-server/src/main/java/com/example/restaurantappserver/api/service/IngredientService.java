package com.example.restaurantappserver.api.service;

import com.example.restaurantappserver.api.dto.IngredientDto;
import com.example.restaurantappserver.entity.Ingredient;

import java.util.List;

public interface IngredientService {
    void saveAll(List<Ingredient> ingredientList);

    List<IngredientDto> getAllIngredientsByRecepieId(Long id);

    void deleteIngredientsByRecipeId(Long id);

    void updateIngredients(List<Ingredient> ingredientList);

    void deleteIngredientById(Long id);
}
