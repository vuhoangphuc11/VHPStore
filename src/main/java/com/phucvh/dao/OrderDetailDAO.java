package com.phucvh.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phucvh.entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{

}
