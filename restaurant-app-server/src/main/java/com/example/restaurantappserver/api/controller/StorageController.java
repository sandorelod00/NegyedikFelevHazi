package com.example.restaurantappserver.api.controller;

import com.example.restaurantappserver.api.dto.ItemDto;
import com.example.restaurantappserver.api.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/storage")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @GetMapping(value = "/listallitem")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ItemDto> getListAllItems(){
        return storageService.getListAllItems();
    }

    @GetMapping(value = "getdrinks")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ItemDto> getDrinks(){return storageService.getDrinks();}

    @GetMapping(value ="getitem")
    @PreAuthorize("hasRole('ADMIN')")
    public ItemDto getItemById(@RequestParam("id") Long id ){
        return storageService.getItemById(id);
    }

    @GetMapping(value = "/getquantityofitembyid")
    @PreAuthorize("hasRole('ADMIN')")
    public ItemDto getQuantityOfItemById(@RequestParam("id")Long id, @RequestBody() double number ){
        return storageService.getQuantityOfItemById(id, number);
    }

    @PostMapping(value = "/additem")
    @PreAuthorize("hasRole('ADMIN')")
    public void postAddNewItem(@RequestBody ItemDto itemDto){
        storageService.addNewItem(itemDto);
    }

    @PutMapping(value = "/updateitem")
    @PreAuthorize("hasRole('ADMIN')")
    public void putUpdateItem(@RequestBody ItemDto itemDto){
        storageService.addNewItem(itemDto);
    }

    @DeleteMapping(value = "/deletitem")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteItem(@RequestParam("id")Long id){
        storageService.deleteItem(id);
    }

    @PostMapping(value = "/addquantity")
    @PreAuthorize("hasRole('ADMIN')")
    public void postAddQuantityToItemById(@RequestParam("id")Long id, @RequestBody() double number ){
        storageService.addQuantityToItemById(id, number);
    }
}
