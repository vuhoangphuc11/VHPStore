package com.phucvh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phucvh.dao.AccountDAO;
import com.phucvh.dao.AuthorityDAO;
import com.phucvh.entity.Account;
import com.phucvh.entity.Authority;
import com.phucvh.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService{
	@Autowired
	AuthorityDAO dao;
	@Autowired
	AccountDAO acdao;
	
	@Override
	public List<Authority> findAll() {
		return dao.findAll();
	}
	
	
	@Override
	public Authority create(Authority auth) {
		return dao.save(auth);
	}
	

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}
	
	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		List<Account> accounts = acdao.getAdministrators();
		return dao.authoritiesOf(accounts);
	}
	
	
}
