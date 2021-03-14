package com.example.restaurantappserver.util;

import com.example.restaurantappserver.api.dto.*;
import com.example.restaurantappserver.entity.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntityConverterToDto {

    public static RecipeDto returnRecipe(Recipe entity, List<IngredientDto> ingredientDtoList) {
        RecipeDto dto = new RecipeDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setIngredients(ingredientDtoList);

        if(ingredientDtoList.stream().filter(i -> i.getIsAvailable() == false).count() > 0 ) {
            dto.setIsAvailable(false);
        }else{dto.setIsAvailable(true);}

        double price = ingredientDtoList.stream().mapToDouble(IngredientDto::getPrice).sum();

        dto.setPrice(price);
        return dto;
    }

    public EmployeeDto returnEmployeeDto(Employee entity) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPosition(entity.getPosition());
        dto.setSalary(entity.getSalary());
        return dto;
    }

    public ItemDto returnItemDto(Item entity){
        ItemDto dto = new ItemDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setItemType(entity.getItemType());
        dto.setPrice(entity.getPrice());
        dto.setTotalQuantity(entity.getTotalQuantity());
        dto.setTotalPrice(entity.getPrice() * entity.getTotalQuantity());
        return dto;
    }

    public IngredientDto returnIngredientDto(Ingredient entity) {
        IngredientDto dto = new IngredientDto();
        dto.setId(entity.getId());
        dto.setItemId(entity.getItem().getId());
        dto.setRecipeId(entity.getRecipe().getId());
        dto.setItemName(entity.getItem().getName());
        dto.setQuantityNeeded(entity.getQuantityNeeded());

        if (entity.getItem().getTotalQuantity() < entity.getQuantityNeeded()){
            dto.setIsAvailable(false);
        }
        else {
            dto.setIsAvailable(true);
        }
        dto.setPrice(dto.getQuantityNeeded() * entity.getItem().getPrice());
        return dto;
    }

    public TableDto returnTableDto(Table entity){
        TableDto dto = new TableDto();
        dto.setId(entity.getId());
        dto.setIsReserved(entity.getIsReserved());
        dto.setRoom(entity.getRoom());
        dto.setUserName(entity.getUserName());
        dto.setCustomerId(entity.getCustomerId());
        return dto;
    }

    public MenuItemDto returnMenuItemDto(MenuItem entity){
        MenuItemDto dto = new MenuItemDto();
        dto.setId(entity.getId());
        dto.setProfitPercent(entity.getProfitPercent());
        if (entity.getRecipe() != null) dto.setRecipeId(entity.getRecipe().getId());
        if (entity.getItem() != null) dto.setItemId(entity.getItem().getId());
        return dto;
    }

    public OrderDto returnOrderDto(Order entity) {
        OrderDto dto = new OrderDto();
        dto.setId(entity.getId());
        dto.setOrderStatus(entity.getOrderStatus());
        dto.setPrice(entity.getPrice());
        dto.setOrderType(entity.getOrderType());
        dto.setOrderStatus(entity.getOrderStatus());

        List<MenuItemDto> menuItemDtoList = new ArrayList<>();
        entity.getMenuItems().forEach(menuItem -> menuItemDtoList.add(returnMenuItemDto(menuItem)));
        dto.setMenuItemsDto(menuItemDtoList);

        if (entity.getCustomer() != null) dto.setCustomerId(entity.getCustomer().getId());
        if (entity.getTable() != null) {
            dto.setTableId(entity.getTable().getId());
            dto.setTableDto(returnTableDto(entity.getTable()));
        };

        return dto;
    }

    public UserDto returnUserDto(User entiy) {
        UserDto dto = new UserDto();
        dto.setId(entiy.getId());
        dto.setPassword(entiy.getPassword());
        dto.setRoles(entiy.getRoles());
        dto.setUserName(entiy.getUserName());
        return dto;
    }
}