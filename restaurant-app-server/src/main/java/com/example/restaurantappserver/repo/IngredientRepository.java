package com.example.restaurantappserver.repo;

import com.example.restaurantappserver.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Query(value = "select i From Ingredient  as i where i.recipe.id = :recipeId")
    List<Ingredient> findAllByRecepieId(Long recipeId);

    @Transactional
    @Modifying
    @Query(value = "DELETE  FROM Ingredient  as i where i.recipe.id = :recipeId")
    void deleteIngredientsByRecipeId(Long recipeId);
}
