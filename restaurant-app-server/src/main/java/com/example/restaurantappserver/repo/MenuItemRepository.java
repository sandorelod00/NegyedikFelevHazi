package com.example.restaurantappserver.repo;

import com.example.restaurantappserver.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    @Query(value = "Select mi from MenuItem as mi where mi.id IN :ids")
    List<MenuItem> getMenuItemBySetOfId(List<Long> ids);
}
