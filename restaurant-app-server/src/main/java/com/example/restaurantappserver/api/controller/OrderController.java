package com.example.restaurantappserver.api.controller;

import com.example.restaurantappserver.api.dto.OrderDto;
import com.example.restaurantappserver.api.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/api/order")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping(path = "/listall")
    public List<OrderDto> getAllOrder(){
        return orderService.getAll();
    }

    @GetMapping(path="/getmyorder")
    public List<OrderDto> getMyOrders(@RequestParam("id") Long id){
        return orderService.getMyOrders(id);
    }

    @PostMapping(path = "/new")
    public void postAddNewOrder(@RequestBody OrderDto orderDto){
        orderService.AddNewOrder(orderDto);
    }

    @DeleteMapping(path = "/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteOrder(@RequestParam("id") Long id){
        orderService.deleteOrder(id);
    }

    @PostMapping(path ="/closeorder")
    @PreAuthorize("hasRole('ADMIN')")
    public void closeOrderById(@RequestParam("id") Long id){orderService.closeOrderById(id);}
}
