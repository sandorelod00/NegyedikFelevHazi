package com.example.restaurantappserver.api.service;

import com.example.restaurantappserver.api.dto.ItemDto;
import com.example.restaurantappserver.entity.Item;
import com.example.restaurantappserver.repo.ItemRepository;
import com.example.restaurantappserver.util.EntityConverterToDto;
import com.example.restaurantappserver.util.DtoConverterToEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final ItemRepository itemRepository;
    private final DtoConverterToEntity dtoConverterToEntity;
    private final EntityConverterToDto entityConverterToDto;

    @Override
    public List<ItemDto> getListAllItems() {
        List<ItemDto> itemDtos = new ArrayList<ItemDto>();
        itemRepository.findAll().forEach(i -> itemDtos.add(entityConverterToDto.returnItemDto(i)));
        return itemDtos;
    }

    @Override
    public ItemDto getItemById(Long id){
        Item item = itemRepository.findById(id).get();
        return entityConverterToDto.returnItemDto(item);
    }

    @Override
    public List<ItemDto> getDrinks() {
        List<ItemDto> itemDtoList = new ArrayList<>();
        itemRepository.getDrinks().forEach(i -> itemDtoList.add(entityConverterToDto.returnItemDto((Item) i)));
        return itemDtoList;
    }

    @Override
    public ItemDto getQuantityOfItemById(Long id, double number) {
        ItemDto item = getItemById(id);
        if (checkQuantityOfItemIsAvailable(item, number)){
            item.setTotalQuantity(item.getTotalQuantity() - number);
            itemRepository.subtractionQuantityOfItemById(id, number);
            return item;
        }
        return null;
    }

    @Override
    public Item findByName(String itemName) {
        return itemRepository.findItemByName(itemName);
    }

    private Boolean checkQuantityOfItemIsAvailable(ItemDto item, Double number){
        return item.getTotalQuantity() >= number ? true : false;
    }

    @Override
    public void addNewItem(ItemDto itemDto) {
        itemRepository.save(dtoConverterToEntity.returnItem(itemDto));
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public void addQuantityToItemById(Long id, double number) {
        itemRepository.AddQuantityToItemById(id, number);
    }
}
