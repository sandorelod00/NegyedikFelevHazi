package com.example.restaurantappserver.repo;

import com.example.restaurantappserver.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE Item as i set i.totalQuantity = i.totalQuantity + :number where i.id = :id")
    void AddQuantityToItemById(Long id, Double number);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Item as i set i.totalQuantity = i.totalQuantity - :number where i.id = :id")
    void subtractionQuantityOfItemById(Long id, Double number);

    @Query(value = "Select i From Item as i where i.name = :name")
    Item findItemByName(String name);

    @Query(value = "Select i from Item  as i where i.itemType <> 0")
    Iterable<Object> getDrinks();
}
