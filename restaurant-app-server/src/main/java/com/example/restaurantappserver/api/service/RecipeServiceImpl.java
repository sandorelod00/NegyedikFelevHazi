package com.example.restaurantappserver.api.service;

import com.example.restaurantappserver.api.dto.IngredientDto;
import com.example.restaurantappserver.api.dto.ItemDto;
import com.example.restaurantappserver.api.dto.RecipeDto;
import com.example.restaurantappserver.entity.Ingredient;
import com.example.restaurantappserver.entity.Item;
import com.example.restaurantappserver.entity.Recipe;
import com.example.restaurantappserver.enums.ItemType;
import com.example.restaurantappserver.repo.IngredientRepository;
import com.example.restaurantappserver.repo.ItemRepository;
import com.example.restaurantappserver.repo.RecipeRepository;
import com.example.restaurantappserver.util.DtoConverterToEntity;
import com.example.restaurantappserver.util.EntityConverterToDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    private final StorageService storageService;
    private final IngredientService ingredientService;

    private final EntityConverterToDto entityConverterToDto;
    private final DtoConverterToEntity dtoConverterToEntity;

    @Override
    public List<RecipeDto> getAllRecipe() {
        List<Recipe> recipes =  recipeRepository.findAll();
        List<RecipeDto> recipeDtoList = new ArrayList<RecipeDto>();
        recipes.forEach(r-> recipeDtoList.add(getRecipe(r)));
        return recipeDtoList;
    }

    @Override
    public RecipeDto getRecipeById(Long id) {
        Recipe recipe = recipeRepository.findById(id).get();
        List<IngredientDto> ingredientDtoList = ingredientService.getAllIngredientsByRecepieId(recipe.getId());
        return EntityConverterToDto.returnRecipe(recipe, ingredientDtoList);
    }

    @Override
    public void deleteRecipeById(Long id) {
        RecipeDto recipeDto = getRecipeById(id);
        ingredientService.deleteIngredientsByRecipeId(id);
        recipeRepository.deleteById(id);
    }

    @Override
    public List<RecipeDto> getAvailableRecipe() {
        return getAllRecipe().stream().filter(r -> r.getIsAvailable() == true).collect(Collectors.toList());
    }

    @Override
    public void updateRecipe(RecipeDto recipeDto) {
        addNewRecipe(recipeDto);
    }

    private RecipeDto getRecipe(Recipe recipe) {
        List<IngredientDto> ingredientDtoList = ingredientService.getAllIngredientsByRecepieId(recipe.getId());
        double price = ingredientDtoList.stream().mapToDouble(IngredientDto::getPrice).sum();
        recipe.setPrice(price);
        return EntityConverterToDto.returnRecipe(recipe, ingredientDtoList);
    }


    @Override
    public void addNewRecipe(RecipeDto recipeDto) {
        Recipe recipeToSave = dtoConverterToEntity.returnRecipe(recipeDto);
        recipeRepository.save(recipeToSave);

        List<Ingredient> ingredientList = getIngredientsOfRecipe(recipeDto, recipeToSave);
        ingredientService.saveAll(ingredientList);
    }

    @Override
    public void deleteIngredientById(Long id) {
        ingredientService.deleteIngredientById(id);
    }

    private List<Ingredient> getIngredientsOfRecipe(RecipeDto recipeDto, Recipe recipeToSave) {
        List<Ingredient> ingredientList = new ArrayList<Ingredient>();
        for(IngredientDto ingredientDto : recipeDto.getIngredients()){
            ItemDto item;
            try{
                item = entityConverterToDto.returnItemDto(storageService.findByName(ingredientDto.getItemName()));
            }
            catch (Exception e){
                item = new ItemDto();
                item.setName(ingredientDto.getItemName());
                item.setItemType(ItemType.INGREDIENT);
                item.setPrice(0.0);
                item.setTotalQuantity(0.0);
                storageService.addNewItem(item);
                item = entityConverterToDto.returnItemDto(storageService.findByName(ingredientDto.getItemName()));
            }
            ingredientDto.setItemId(item.getId());
            if (item.getTotalQuantity() < ingredientDto.getQuantityNeeded() || item.getPrice() == 0.0) {
                ingredientDto.setIsAvailable(false);
            } else {
                ingredientDto.setIsAvailable(true);
            }
            ingredientDto.setPrice(ingredientDto.getQuantityNeeded() * item.getPrice());
            ingredientDto.setRecipeId(recipeToSave.getId());
            ingredientDto.setRecipeId(recipeToSave.getId());
            ingredientDto.setItemId(item.getId());
            ingredientList.add(dtoConverterToEntity.returnIngredient(ingredientDto, recipeToSave, dtoConverterToEntity.returnItem(item)));
        }
        return ingredientList;
    }
}
