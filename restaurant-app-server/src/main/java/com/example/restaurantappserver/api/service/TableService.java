package com.example.restaurantappserver.api.service;

import com.example.restaurantappserver.api.dto.TableDto;

import java.util.List;

public interface TableService {
    List<TableDto> getAllTable();

    List<TableDto> getAvailableTabeles();

    void addTable(TableDto tableDto);

    TableDto reserveTable(TableDto id);

    void deleteTable(Long id);

    TableDto getTableById(Long id);
}
