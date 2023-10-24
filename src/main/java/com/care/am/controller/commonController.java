package com.care.am.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class commonController {
	
	@GetMapping("/") //메인 홈페이지
	public String main() {
		return "am/common/main";
	}
	//css 확인 때문에 만듦 & 삭제 예정
	@GetMapping("/main2") //메인 홈페이지2
	public String main2() {
		return "am/common/main2";
	}
	
	@GetMapping("mediSearch") //병원 찾기 페이지
	public String mediSearch() {
		return "am/common/mediSearch";
	}
	
	@RequestMapping("logout") //로그아웃
	public String logout() {
		return "am/common/main";
	}
	

	

}
