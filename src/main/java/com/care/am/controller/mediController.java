package com.care.am.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class mediController {
	
	//�α��� ����
	@GetMapping("mediRegister") //���� ȸ������
	public String mediRegister() {
		return "am/medi/mediRegister";
	}
	
	@PostMapping("mediRegister") //���� ȸ������ ����
	public void mediRegister(String id) {
		
	}
	
	@PostMapping("mediLogin") //���� �α��� Ȯ��
	public void loginChk() {
		
	}
	
	//�������� ����
	@GetMapping("mediInfo") // ���� �������� ������
	public String info() {
		return "am/medi/mediInfo";
	}

	@GetMapping("mediModify") // ���� �������� ���� ������
	public String modify() {
		return "am/medi/mediModify";
	}

	@PostMapping("mediModify") // ���� �������� ���� ����
	public void modify(String id) {

	}

	@PostMapping("mediDelete") // ���� Ż��
	public void delete() {

	}
}
