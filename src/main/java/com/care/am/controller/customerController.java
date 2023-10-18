package com.care.am.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class customerController {
	
	//�α��� ����
	@GetMapping("customerRegister") //�մ� ȸ������ ������
	public String register() {
		return "am/customer/customerRegister";
	}
	
	@PostMapping("customerRegister") //�մ� ȸ������ ����
	public void register(String id) {
		
	}
	
	@PostMapping("customerLogin") //�մ� �α��� Ȯ��
	public void loginChk() {
		
	}
	
	//�մ����� ����
	@GetMapping("customerInfo") //�մ� �������� ������
	public String info() {
		return "am/customer/customerInfo";
	}
	
	@GetMapping("customerModify") //�մ� �������� ���� ������
	public String modify() {
		return "am/customer/customerModify";
	}
	
	@PostMapping("customerModify") //�մ� �������� ���� ����
	public void modify(String id) {
		
	}
	
	@GetMapping("customerDelete") //�մ� Ż�� ������
	public String delete() {
		return "am/customer/customerDelete";
	}
	
	@PostMapping("customerDelete") //�մ� Ż��
	public void delete(String id, String pw) {
		// �Ű����� ���Ƿ� �־� ��
	}
}
