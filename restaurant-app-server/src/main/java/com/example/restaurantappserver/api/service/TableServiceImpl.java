package com.example.restaurantappserver.api.service;

import com.example.restaurantappserver.entity.Table;
import com.example.restaurantappserver.api.dto.TableDto;
import com.example.restaurantappserver.repo.TableRepository;
import com.example.restaurantappserver.util.DtoConverterToEntity;
import com.example.restaurantappserver.util.EntityConverterToDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {

    private final TableRepository tableRepository;

    private final DtoConverterToEntity dtoConverterToEntity;
    private final EntityConverterToDto entityConverterToDto;

    private final UserService userService;

    @Override
    public List<TableDto> getAllTable() {
        List<TableDto> tableDtoList = new ArrayList<>();
        tableRepository.findAll().forEach(t -> tableDtoList.add(entityConverterToDto.returnTableDto(t)));
        return tableDtoList;
    }

    @Override
    public TableDto getTableById(Long id){
        return entityConverterToDto.returnTableDto(tableRepository.findById(id).get());
    }

    @Override
    public List<TableDto> getAvailableTabeles() {
        List<TableDto> tableDtoList = new ArrayList<>();
        tableRepository.findAllAvailable().forEach(t -> tableDtoList.add(entityConverterToDto.returnTableDto(t)));
        return tableDtoList;
    }

    @Override
    public void addTable(TableDto tableDto) {
        tableRepository.save(dtoConverterToEntity.returnTable(tableDto));
    }

    @Override
    public TableDto reserveTable(TableDto tableDto) {
        Table t = tableRepository.findById(tableDto.getId()).get();
        t.setIsReserved(!t.getIsReserved());
        if(t.getIsReserved() == true){
            if(tableDto.getCustomerId() != null){
                t.setCustomerId(tableDto.getCustomerId());
                t.setUserName(userService.finyById(tableDto.getCustomerId()).getUserName());
            }else{
                t.setUserName("Reserved By Waiter");
            }
        }else{
            t.setCustomerId(null);
            t.setUserName(null);
        }
        tableRepository.save(t);
        return entityConverterToDto.returnTableDto(t);
    }

    @Override
    public void deleteTable(Long id) {
        tableRepository.deleteById(id);
    }
}
