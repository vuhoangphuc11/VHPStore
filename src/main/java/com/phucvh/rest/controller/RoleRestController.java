package com.phucvh.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phucvh.entity.Role;
import com.phucvh.service.RoleService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/roles")
public class RoleRestController {
	@Autowired
	RoleService roleService;
	
	@GetMapping
	public List<Role> getall(){
		return roleService.findAll();
	}

}
