package com.phucvh.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phucvh.dao.AccountDAO;
import com.phucvh.entity.Account;
import com.phucvh.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	AccountDAO dao;
	@Autowired
	HttpServletRequest request;
	
	public String getString(String name, String defaultValue){
		String value = request.getParameter(name);
		return value != null ? value : defaultValue;
	}

	@Override
	public Account findById(String username) {
		return dao.findById(username).get();
	}

	@Override
	public List<Account> findAll() {
		return dao.findAll();
	}
	

	@Override
	public List<Account> getAdministrators() {
		return dao.getAdministrators();
	}

	@Override
	public Account create(Account account) {
		return dao.save(account);
	}

	@Override
	public List<Account> getAll() {
		return dao.findAll();
	}

	@Override
	public Account update(Account account) {
		return dao.save(account);
	}

	@Override
	public void delete(String username) {
		dao.deleteById(username);
	}

}
