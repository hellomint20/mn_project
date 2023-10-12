package com.care.am.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class mediController {
	
	//로그인 관련
	@GetMapping("mediRegister") //병원 회원가입
	public String mediRegister() {
		return "am/medi/mediRegister";
	}
	
	@PostMapping("mediRegister") //병원 회원가입 적용
	public void mediRegister(String id) {
		
	}
	
	@PostMapping("mediLogin") //병원 로그인 확인
	public void loginChk() {
		
	}
	
	//개인정보 관련
	@GetMapping("mediInfo") // 병원 개인정보 페이지
	public String info() {
		return "am/medi/mediInfo";
	}

	@GetMapping("mediModify") // 병원 개인정보 수정 페이지
	public String modify() {
		return "am/medi/mediModify";
	}

	@PostMapping("mediModify") // 병원 개인정보 수정 적용
	public void modify(String id) {

	}

	@PostMapping("mediDelete") // 병원 탈퇴
	public void delete() {

	}
}
