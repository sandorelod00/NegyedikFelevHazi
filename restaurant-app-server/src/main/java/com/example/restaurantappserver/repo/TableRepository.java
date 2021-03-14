package com.example.restaurantappserver.repo;

import com.example.restaurantappserver.entity.Recipe;
import com.example.restaurantappserver.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface TableRepository  extends JpaRepository<Table, Long> {

    @Query(value = "select t  from Table  as t where t.isReserved = false ")
    List<Table> findAllAvailable();

}
