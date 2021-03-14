package com.example.restaurantappserver.api.service;

import com.example.restaurantappserver.api.dto.RecipeDto;
import com.example.restaurantappserver.entity.Recipe;

import java.util.List;

public interface RecipeService {
    void addNewRecipe(RecipeDto recipeDto);

    List<RecipeDto> getAllRecipe();

    RecipeDto getRecipeById(Long id);

    void deleteRecipeById(Long id);

    List<RecipeDto> getAvailableRecipe();

    void updateRecipe(RecipeDto recipeDto);

    void deleteIngredientById(Long id);
}
