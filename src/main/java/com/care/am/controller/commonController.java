package com.care.am.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.care.am.common.LoginSession;
import com.care.am.service.customer.customerService;

@Controller
public class commonController {
	
	@Autowired customerService cs;
	
	@GetMapping("/") //���� Ȩ������
	public String main() {
		return "am/common/main";
	}
	
	//css Ȯ�� ������ ���� & ���� ����
	@GetMapping("/main2") //���� Ȩ������2
	public String main2() {
		return "am/common/main2";
	}
	
	@GetMapping("mediSearch") //���� ã�� ������
	public String mediSearch() {
		return "am/common/mediSearch";
	}
	
	@RequestMapping("logout") //�α׾ƿ�
	public String logout(HttpSession session) {
		System.out.println("logout: "+ LoginSession.LOGIN);
		session.invalidate();
		return "am/common/main";
	}


}
