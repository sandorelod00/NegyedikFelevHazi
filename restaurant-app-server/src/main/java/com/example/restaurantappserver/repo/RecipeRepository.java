package com.example.restaurantappserver.repo;

import com.example.restaurantappserver.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
