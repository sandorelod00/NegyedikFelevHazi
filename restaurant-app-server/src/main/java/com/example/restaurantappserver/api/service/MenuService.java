package com.example.restaurantappserver.api.service;

import com.example.restaurantappserver.api.dto.MenuItemDto;

import java.util.List;

public interface MenuService {
    void addMenuItem(MenuItemDto menuItemDto);

    List<MenuItemDto> getMenu();

    void removeMenuItem(Long id);

    List<MenuItemDto> getMenuItemBySetOfId(List<Long> ids);
}
