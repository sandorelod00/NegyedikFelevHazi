package com.example.restaurantappserver.api.service;

import com.example.restaurantappserver.api.dto.ItemDto;
import com.example.restaurantappserver.entity.Item;

import java.util.List;

public interface StorageService {
    List<ItemDto> getListAllItems();

    void addNewItem(ItemDto itemDto);

    void deleteItem(Long id);

    void addQuantityToItemById(Long id, double number);

    ItemDto getQuantityOfItemById(Long id, double number);

    Item findByName(String itemName);

    ItemDto getItemById(Long id);

    List<ItemDto> getDrinks();
}
