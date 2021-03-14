package com.example.restaurantappserver.util;

import com.example.restaurantappserver.api.dto.*;
import com.example.restaurantappserver.entity.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DtoConverterToEntity {

    public Employee returnEmployee(EmployeeDto dto){
        Employee entity = new Employee();
        if(dto.getId() != null){
            entity.setId(dto.getId());
        }
        entity.setSalary(dto.getSalary());
        entity.setPosition(dto.getPosition());
        entity.setLastName(dto.getLastName());
        entity.setFirstName(dto.getFirstName());
        return entity;
    }

    public Item returnItem(ItemDto dto){
        Item entity = new Item();
        if (dto.getId() != null){
            entity.setId(dto.getId());
        }
        entity.setName(dto.getName());
        entity.setItemType(dto.getItemType());
        entity.setPrice(dto.getPrice());
        entity.setTotalQuantity(dto.getTotalQuantity());
        return entity;
    }

    public Recipe returnRecipe(RecipeDto dto){
        Recipe entity = new Recipe();
        if(dto.getId() != null){
            entity.setId(dto.getId());
        }
        entity.setName(dto.getName());

        return entity;
    }

    public Ingredient returnIngredient(IngredientDto dto, Recipe recipe, Item item) {
        Ingredient entity = new Ingredient();
        if(dto.getId() != null){
            entity.setId(dto.getId());
        }
        entity.setRecipe(recipe);
        entity.setItem(item);
        entity.setQuantityNeeded(dto.getQuantityNeeded());
        entity.setPrice(dto.getPrice());
        entity.setIsAvailable(dto.getIsAvailable());
        return  entity;
    }

    public Table returnTable(TableDto dto){
        Table entity = new Table();
        if(dto.getId() != null){
            entity.setId(dto.getId());
        }
        entity.setIsReserved(dto.getIsReserved());
        entity.setRoom(dto.getRoom());
        return entity;
    }

    public MenuItem returnMenuItem(MenuItemDto dto){
        MenuItem entity = new MenuItem();
        if (dto.getId() != null){
            entity.setId(dto.getId());
        }
        entity.setProfitPercent(dto.getProfitPercent());
        if(dto.getItemId() != null) entity.setItem(returnItem(dto.getItemDto()));
        if (dto.getRecipeId() != null) entity.setRecipe(returnRecipe(dto.getRecipeDto()));
        return entity;
    }

    public Order returnOrder(OrderDto dto) {
        Order entity = new Order();
        if(dto.getId() != null){
            entity.setId(dto.getId());
        }

        List<MenuItem> menuItems = new ArrayList<>();
        dto.getMenuItemsDto().forEach(menuItemDto -> menuItems.add(returnMenuItem(menuItemDto)) );
        entity.setMenuItems(menuItems);

        if(dto.getTableDto() != null) entity.setTable(returnTable(dto.getTableDto()));
        if(dto.getCustomer() != null) entity.setCustomer(returUser(dto.getCustomer()));


        entity.setOrderStatus(dto.getOrderStatus());
        entity.setOrderType(dto.getOrderType());
        entity.setPrice(dto.getPrice());

        return entity;
    }

    private User returUser(UserDto dto) {
        User entity = new User();
        if(dto.getId() != null) {
            entity.setId(dto.getId());
        }
        entity.setUserName(dto.getUserName());
        entity.setPassword(dto.getPassword());
        entity.setRoles(dto.getRoles());
        return entity;
    }
}
