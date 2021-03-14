package com.example.restaurantappserver.api.service;

import com.example.restaurantappserver.api.dto.IngredientDto;
import com.example.restaurantappserver.entity.Ingredient;
import com.example.restaurantappserver.repo.IngredientRepository;
import com.example.restaurantappserver.util.EntityConverterToDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService{

    private final IngredientRepository ingredientRepository;

    private final EntityConverterToDto entityConverterToDto;

    @Override
    public void saveAll(List<Ingredient> ingredientList) {
        ingredientRepository.saveAll(ingredientList);
    }

    @Override
    public List<IngredientDto> getAllIngredientsByRecepieId(Long recipeId) {
        List<IngredientDto> ingredientDtoList =  new ArrayList<IngredientDto>();
       for (Ingredient ingredient : ingredientRepository.findAllByRecepieId(recipeId)){
           ingredientDtoList.add(entityConverterToDto.returnIngredientDto(ingredient));
        }
        return ingredientDtoList;
    }

    @Override
    public void deleteIngredientsByRecipeId(Long recipeId) {
        ingredientRepository.deleteIngredientsByRecipeId(recipeId);
    }

    @Override
    public void updateIngredients(List<Ingredient> ingredientList) {
        for(Ingredient ingredient : ingredientList){

        }
    }

    @Override
    public void deleteIngredientById(Long id) {
        ingredientRepository.deleteById(id);
    }
}
