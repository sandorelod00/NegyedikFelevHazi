package com.example.restaurantappserver.entity;

import com.example.restaurantappserver.enums.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "total_quantity")
    private Double totalQuantity;

    @Column(name = "price")
    private Double price;

    @Column(name = "item_type")
    private ItemType itemType;
}
