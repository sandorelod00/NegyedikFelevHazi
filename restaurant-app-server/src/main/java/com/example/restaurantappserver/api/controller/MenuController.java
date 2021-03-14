package com.example.restaurantappserver.api.controller;

import com.example.restaurantappserver.api.dto.MenuItemDto;
import com.example.restaurantappserver.api.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/api/menu")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping(path = "/list")
    @PreAuthorize("hasRole('ADMIN')")
    public List<MenuItemDto> getMenu(){
        return menuService.getMenu();
    }

    @PostMapping(path = "/addmenuitem")
    @PreAuthorize("hasRole('ADMIN')")
    public void addMenuItem(@RequestBody MenuItemDto menuItemDto){
        menuService.addMenuItem(menuItemDto);
    }

    @DeleteMapping(path = "/remove")
    @PreAuthorize("hasRole('ADMIN')")
    public void removeMenuItem(@RequestParam("id") Long id){
        menuService.removeMenuItem(id);
    }

    @PutMapping(path ="/update")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateMenuItem(@RequestBody MenuItemDto menuItemDto){menuService.addMenuItem(menuItemDto);}
}
