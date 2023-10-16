package com.care.am.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class commonController {
	
	@GetMapping("main") //메인 홈페이지
	public String main() {
		return "am/common/main";
	}
	
	@GetMapping("mediSearch") //병원 찾기 페이지
	public String mediSearch() {
		return "am/common/mediSearch";
	}
	
	@GetMapping("login") //로그인 페이지
	public String login() {
		return "am/common/login";
	}
	
	@PostMapping("logout") //로그아웃
	public String logout() {
		return "am/common/login";
	}
	

}
