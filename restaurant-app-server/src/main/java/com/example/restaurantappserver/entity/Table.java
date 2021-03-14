package com.example.restaurantappserver.entity;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@javax.persistence.Table(name = "Tables_in_Restaurant")
public class Table {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long Id;

    @Column(name = "is_reserved")
    private Boolean isReserved;

    @Column(name = "room")
    private Long room;

    @Nullable
    @Column(name ="customer_id")
    private Long customerId;

    @Nullable
    @Column(name = "user_name")
    private String userName;
}
