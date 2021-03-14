package com.example.restaurantappserver.entity;

import com.example.restaurantappserver.api.dto.UserDto;
import com.example.restaurantappserver.enums.OrderStatus;
import com.example.restaurantappserver.enums.OrderType;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.zip.CheckedOutputStream;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@javax.persistence.Table(name = "orders")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "order_type")
    private OrderType orderType;

    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "price")
    private Double price;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    @Nullable
    private User customer;

    @ManyToOne(targetEntity = Table.class)
    @JoinColumn(name = "table_id")
    @Nullable
    private Table table;

    @ManyToMany(targetEntity = MenuItem.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id")
    private List<MenuItem> menuItems;
}
