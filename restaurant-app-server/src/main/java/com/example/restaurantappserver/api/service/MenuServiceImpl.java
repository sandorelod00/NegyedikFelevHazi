package com.example.restaurantappserver.api.service;

import com.example.restaurantappserver.api.dto.ItemDto;
import com.example.restaurantappserver.api.dto.MenuItemDto;
import com.example.restaurantappserver.api.dto.RecipeDto;
import com.example.restaurantappserver.entity.MenuItem;
import com.example.restaurantappserver.repo.MenuItemRepository;
import com.example.restaurantappserver.util.DtoConverterToEntity;
import com.example.restaurantappserver.util.EntityConverterToDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuItemRepository menuItemRepository;
    private final DtoConverterToEntity dtoConverterToEntity;
    private final EntityConverterToDto entityConverterToDto;

    private final StorageService storageService;
    private final RecipeService recipeService;


    @Override
    public List<MenuItemDto> getMenu() {
        List<MenuItemDto> menuItemDtoList = new ArrayList<>();
        menuItemRepository.findAll().forEach(mi -> menuItemDtoList.add(entityConverterToDto.returnMenuItemDto(mi)));
        for (MenuItemDto dto : menuItemDtoList) {
            if (dto.getRecipeId() != null) {
                dto.setRecipeDto(recipeService.getRecipeById(dto.getRecipeId()));
                dto.setPrice(dto.getRecipeDto().getPrice() + dto.getRecipeDto().getPrice() * dto.getProfitPercent());
            }
            if (dto.getItemId() != null) {
                dto.setItemDto(storageService.getItemById(dto.getItemId()));
                dto.setPrice(dto.getItemDto().getPrice() + dto.getItemDto().getPrice() * dto.getProfitPercent());
            }
        }
        return menuItemDtoList;
    }

    public List<MenuItemDto> getMenuItemBySetOfId(List<Long> ids){
        List<MenuItemDto> menuItemDtoList = new ArrayList<>();
        menuItemRepository.getMenuItemBySetOfId(ids).forEach(mi -> menuItemDtoList.add(entityConverterToDto.returnMenuItemDto(mi)));
        for (MenuItemDto dto : menuItemDtoList) {
            if (dto.getRecipeId() != null) {
                dto.setRecipeDto(recipeService.getRecipeById(dto.getRecipeId()));
                dto.setPrice(dto.getRecipeDto().getPrice() + dto.getRecipeDto().getPrice() * dto.getProfitPercent());
            }
            if (dto.getItemId() != null) {
                dto.setItemDto(storageService.getItemById(dto.getItemId()));
                dto.setPrice(dto.getItemDto().getPrice() + dto.getItemDto().getPrice() * dto.getProfitPercent());
            }
        }
        return menuItemDtoList;
    }

    @Override
    public void removeMenuItem(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id).get();

        menuItemRepository.deleteById(id);
    }

    @Override
    public void addMenuItem(MenuItemDto menuItemDto) {
        if (menuItemDto.getRecipeId() != null) {
            menuItemDto.setRecipeDto(recipeService.getRecipeById(menuItemDto.getRecipeId()));
        }
        if (menuItemDto.getItemId() != null) {
            menuItemDto.setItemDto(storageService.getItemById(menuItemDto.getItemId()));
        }
        menuItemRepository.save(dtoConverterToEntity.returnMenuItem(menuItemDto));
    }

}
