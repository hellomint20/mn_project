package com.care.am.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pet")
public class petController {
	
	@GetMapping("petList") //�� ����Ʈ
	public String petList() {
		return "am/pet/petList";
	}
	
	@GetMapping("petRegister") //�� ���
	public String petRegister() {
		return "am/pet/petRegister";
	}
	
	@GetMapping("petModify") //�� ���� ����
	public String petModify() {
		return "am/pet/petModify";
	}
	
	@PostMapping("petModify") //�� ���� ���� ����
	public void petModify(String id) {
		
	}
	
	@PostMapping("petDelete") //�� ���� ����
	public void petDelete(String id) {
		
	}
}
