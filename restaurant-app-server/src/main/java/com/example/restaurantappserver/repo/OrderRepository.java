package com.example.restaurantappserver.repo;

import com.example.restaurantappserver.entity.Order;
import com.example.restaurantappserver.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "Select o from Order as o order by o.id desc ")
    List<Order> findAll();

    @Query(value = "Select o from Order as o where o.customer.id = :id order by o.id desc ")
    List<Order> findByCustomerId(Long id);

    @Transactional
    @Modifying
    @Query(value = "update Order  o set o.orderStatus = :type where o.id = :id ")
    void closeorderById(Long id, OrderStatus type);
}
