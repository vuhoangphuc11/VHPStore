package com.phucvh.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phucvh.dao.AccountDAO;
import com.phucvh.entity.Account;
import com.phucvh.service.UserService;
import com.phucvh.service.impl.AccountServiceImpl;


@Controller
public class SecurityController {
	@Autowired
	UserService userService;
	@Autowired
	AccountServiceImpl accountImpl;
	@Autowired
	AccountDAO dao;

	
	@RequestMapping("/security/login/form")
	public String loginForm(Model model) {
		model.addAttribute("message", "Vui lòng đăng nhập!");
		return "security/login";
	}
	
	@RequestMapping("/security/signup/form")
	public String signupForm(Model model) {
		model.addAttribute("message", "Vui lòng nhập thông tin!");
		return "security/signup";
	}
	
	@RequestMapping("/security/signup/success")	
	public String signupSuccess(Model model) {
		try {
			String username = accountImpl.getString("username", "");
			String fullname = accountImpl.getString("fullname", "");
			String password = accountImpl.getString("password", "");
			String email = accountImpl.getString("email", "");
			String photo = accountImpl.getString("photo", "");
		
			
			Account account = new Account();
			account.setUsername(username);
			account.setFullname(fullname);	
			account.setPassword(password);
			account.setEmail(email);
			account.setPhoto(photo);
			
			Account a = dao.findById(username).orElse(null);
			
			if(a!=null) {
				model.addAttribute("message", "Tạo tài khoản thất bại!");
				return "security/signup";
			}
			dao.save(account);
			model.addAttribute("message", "Tạo tài khoản thành công!");
			return "security/signup";		
		} catch (Exception e) {
			e.printStackTrace();
			
			return "redirect:/security/signup/error";
		}
	
	}
	
	@RequestMapping("/security/signup/error")	
	public String signupError(Model model) {
	
		model.addAttribute("message", "Tạo tài khoản thất bại!");
		return "security/signup";
	}
	
	@RequestMapping("/security/login/success")	
	public String loginSuccess(Model model) {
		model.addAttribute("message", "Đăng nhập thành công!");
		return "security/login";
	}
	
	@RequestMapping("/security/login/error")	
	public String loginError(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập!");
		return "security/login";
	}
	
	@RequestMapping("/security/login/unauthoried")	
	public String Unauthoried(Model model) {
		model.addAttribute("message", "Bạn không có quyền truy cập trang này!");
		return "security/login";
	}
	
	@RequestMapping("/security/logoff/success")	
	public String logoffSuccess(Model model) {
		model.addAttribute("message", "Đăng xuất thành công!");
		return "security/login";
	}
	
	@RequestMapping("/oauth2/login/success")
	public String success(OAuth2AuthenticationToken oauth2) {
		userService.loginFromOAuth2(oauth2);
		return "forward:/security/logoff/success";
	}
	
}
