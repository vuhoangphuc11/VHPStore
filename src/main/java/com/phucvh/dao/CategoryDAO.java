package com.phucvh.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phucvh.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, String>{

}
