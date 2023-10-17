package com.care.am.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class customerController {
	
	//로그인 관련
	@GetMapping("customerRegister") //손님 회원가입 페이지
	public String register() {
		return "am/customer/customerRegister";
	}
	
	@PostMapping("customerRegister") //손님 회원가입 적용
	public void register(String id) {
		
	}
	
	@PostMapping("customerLogin") //손님 로그인 확인
	public void loginChk() {
		
	}
	
	//손님정보 관련
	@GetMapping("customerInfo") //손님 개인정보 페이지
	public String info() {
		return "am/customer/customerInfo";
	}
	
	@GetMapping("customerModify") //손님 개인정보 수정 페이지
	public String modify() {
		return "am/customer/customerModify";
	}
	
	@PostMapping("customerModify") //손님 개인정보 수정 적용
	public void modify(String id) {
		
	}
	
	@GetMapping("customerDelete") //손님 탈퇴 페이지
	public String delete() {
		return "am/customer/customerDelete";
	}
	
	@PostMapping("customerDelete") //손님 탈퇴
	public void delete(String id, String pw) {
		// 매개변수 임의로 넣어 둠
	}
}
