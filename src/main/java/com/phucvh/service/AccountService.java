package com.phucvh.service;

import java.util.List;

import com.phucvh.entity.Account;

public interface AccountService {
	
	public List<Account> findAll();
	public Account findById(String username);	
	public List<Account> getAdministrators();
	public Account create(Account account);
	public List<Account> getAll();
	public Account update(Account account);
	public void delete(String username);
	
}
