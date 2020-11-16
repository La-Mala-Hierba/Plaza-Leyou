package com.leyou.order.repository;

import com.leyou.order.pojo.Order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {



    @Query(value = "SELECT * FROM tb_order WHERE user_id = ?1 AND order_id IN (SELECT order_id FROM tb_order_status WHERE STATUS = ?2) order by tb_order.create_time DESC",
            nativeQuery = true)
    public Page<Order> queryOrderList(Long userId, Integer status, Pageable pageable);

    @Query(value = "SELECT * FROM tb_order WHERE user_id = ? order by tb_order.create_time DESC", nativeQuery = true)
    public Page<Order> queryOrderList(Long userId, Pageable pageable);
}
