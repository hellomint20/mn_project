package com.care.am.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class commonController {
	
	@GetMapping("main") //���� Ȩ������
	public String main() {
		return "am/common/main";
	}
	
	@GetMapping("mediSearch") //���� ã�� ������
	public String mediSearch() {
		return "am/common/mediSearch";
	}
	
	@GetMapping("login") //�α��� ������
	public String login() {
		return "am/common/login";
	}
	
	@PostMapping("logout") //�α׾ƿ�
	public String logout() {
		return "am/common/login";
	}
	

}
