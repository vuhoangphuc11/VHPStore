package com.phucvh.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.phucvh.entity.Order;

public interface OrderService {

	Order create(JsonNode orderData);

	Order findById(Integer id);

	List<Order> findByUsername(String username);

	List<Order> findAll();

	Order findById(Long id);

	Order update(Order order);

	void delete(Long id);

	


}
