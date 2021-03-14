package com.example.restaurantappserver.api.controller;

import com.example.restaurantappserver.api.dto.TableDto;
import com.example.restaurantappserver.api.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/table")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;

    @GetMapping(value = "/all")
    public List<TableDto> getAllTable(){
        return tableService.getAllTable();
    }

    @GetMapping(value = "/available")
    public List<TableDto> getAvailableTables(){
        return tableService.getAvailableTabeles();
    }

    @PostMapping(value = "/add")
    @PreAuthorize("hasRole('ADMIN')")
    public void postAddTable(@RequestBody TableDto tableDto){
        tableService.addTable(tableDto);
    }

    @PostMapping(value = "/reserve")
    public TableDto postReserveTable(@RequestBody TableDto tableDto){
        return tableService.reserveTable(tableDto);
    }

    @DeleteMapping(value = "/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTable(@RequestParam("id") Long id){
        tableService.deleteTable(id);
    }
}
