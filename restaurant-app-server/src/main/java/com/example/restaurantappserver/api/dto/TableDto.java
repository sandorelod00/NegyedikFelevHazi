package com.example.restaurantappserver.api.dto;

import com.example.restaurantappserver.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableDto {
    private Long id;
    private Boolean isReserved;
    private Long room;
    private Long customerId;
    private String userName;
}
