package com.example.restaurantappserver.api.controller;

import com.example.restaurantappserver.api.dto.RecipeDto;
import com.example.restaurantappserver.api.service.RecipeService;
import com.example.restaurantappserver.entity.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/recipe")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping(path = "/getallrecipe")
    @PreAuthorize("hasRole('ADMIN')")
    public List<RecipeDto> getAllRecpie() {
        return recipeService.getAllRecipe();
    }

    @GetMapping(path = "/getAvailableRecipe")
    @PreAuthorize("hasRole('ADMIN')")
    public List<RecipeDto> getAvailableRecipe() {
        return recipeService.getAvailableRecipe();
    }

    @GetMapping(path = "/getrecipebyid")
    @PreAuthorize("hasRole('ADMIN')")
    public RecipeDto getRecipeById(@RequestParam("id") Long id) {
        return recipeService.getRecipeById(id);
    }

    @PostMapping(path = "/addnewrecipe")
    @PreAuthorize("hasRole('ADMIN')")
    public void addNewRecipe(@RequestBody RecipeDto recipeDto) {
        recipeService.addNewRecipe(recipeDto);
    }

    @PutMapping(path = "/updaterecipe")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateRecipe(@RequestBody RecipeDto recipeDto) {
        recipeService.updateRecipe(recipeDto);
    }

    @DeleteMapping(path = "/deleterecipe")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteRecipe(@RequestParam("id") Long id) {
        recipeService.deleteRecipeById(id);
    }

    @DeleteMapping(path ="deleteingredientbyid")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteIngredientById(@RequestParam("id") Long id){
        recipeService.deleteIngredientById(id);
    }
}
