package com.care.am.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.am.service.medi.mediService;

@Controller
public class mediController {
	
	@Autowired mediService ms;
	
	//로그인 관련
	@GetMapping("mediRegister") //병원 회원가입
	public String mediRegister() {
		return "am/medi/mediRegister";
	}
	
	@PostMapping("mediRegister") //병원 회원가입 적용
	public void mediRegister(String id) {
		
	}
	
	@GetMapping("mediLogin") //로그인 페이지
	public String mediLogin() {
		return "am/medi/mediLogin";
	}
	
	@PostMapping("mediLogin") //병원 로그인 확인
	public void loginChk() {
		
	}
	@GetMapping("mediSearchIdPw") //아이디/비밀번호 찾기 페이지
	public String SearchIdPw() {
		return "am/medi/mediSearchIdPw";
	}
	
	//개인정보 관련
	@GetMapping("mediInfo") // 병원 개인정보 페이지
	//public String info(@RequestParam String id, Model model) {
	public String info(String id, Model model) {
		model.addAttribute("info", ms.getMedi(id));
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
