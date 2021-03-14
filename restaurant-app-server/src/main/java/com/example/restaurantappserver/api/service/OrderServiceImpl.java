package com.example.restaurantappserver.api.service;

import com.example.restaurantappserver.api.dto.MenuItemDto;
import com.example.restaurantappserver.api.dto.OrderDto;
import com.example.restaurantappserver.entity.MenuItem;
import com.example.restaurantappserver.entity.Order;
import com.example.restaurantappserver.enums.OrderStatus;
import com.example.restaurantappserver.enums.OrderType;
import com.example.restaurantappserver.repo.OrderRepository;
import com.example.restaurantappserver.util.DtoConverterToEntity;
import com.example.restaurantappserver.util.EntityConverterToDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    private final StorageService storageService;
    private final TableService tableService;
    private final UserService userService;
    private final MenuService menuService;

    private final DtoConverterToEntity dtoConverterToEntity;
    private final EntityConverterToDto entityConverterToDto;

    @Override
    public List<OrderDto> getAll() {
        List<OrderDto> orderDtoList = new ArrayList<>();
        orderRepository.findAll().forEach(entity -> orderDtoList.add(entityConverterToDto.returnOrderDto(entity)));
        return orderDtoList;
    }

    @Override
    public void AddNewOrder(OrderDto orderDto) {
        List<MenuItemDto> menuItemList = new ArrayList<>();
        Map<Long, MenuItemDto> menuItemDtoHashMap = menuService.getMenuItemBySetOfId(orderDto.getMenuItems()).stream().collect(Collectors.toMap(MenuItemDto::getId, menuItemDto -> menuItemDto));
        Double price = 0.0;
        //Remove Items From Storage
        for(Long id : orderDto.getMenuItems()){
            if (menuItemDtoHashMap.get(id).getItemId() != null ){
                storageService.addQuantityToItemById(menuItemDtoHashMap.get(id).getItemId(), -1);
            }
            if (menuItemDtoHashMap.get(id).getRecipeId() != null){
                menuItemDtoHashMap.get(id).getRecipeDto().getIngredients().forEach(i -> storageService.addQuantityToItemById(i.getItemId(), i.getQuantityNeeded() * -1));
            }
            price += menuItemDtoHashMap.get(id).getPrice();
        }

        if(orderDto.getTableId() != null){
            orderDto.setTableDto(tableService.getTableById(orderDto.getTableId()));
            if(orderDto.getTableDto().getIsReserved() == false){
                if(orderDto.getCustomerId() != null){
                    orderDto.getTableDto().setCustomerId(orderDto.getCustomerId());
                    orderDto.getTableDto().setUserName(userService.finyById(orderDto.getCustomerId()).getUserName());
                }
                tableService.reserveTable(orderDto.getTableDto());
            }
        }
        if(orderDto.getCustomerId() != null){
            orderDto.setCustomer(userService.finyById(orderDto.getCustomerId()));
        }

        orderDto.setPrice(price);
        orderDto.setOrderStatus(OrderStatus.UNDER_PROCESS);
        orderRepository.save(dtoConverterToEntity.returnOrder(orderDto));
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderDto> getMyOrders(Long id) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        orderRepository.findByCustomerId(id)
                .forEach( o ->
                    orderDtoList.add(entityConverterToDto.returnOrderDto(o))
                );
        return orderDtoList;
    }

    @Override
    public void closeOrderById(Long id) {
        orderRepository.closeorderById(id, OrderStatus.CLOSED);
    }
}
