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
	
	//�α��� ����
	@GetMapping("mediRegister") //���� ȸ������
	public String mediRegister() {
		return "am/medi/mediRegister";
	}
	
	@PostMapping("mediRegister") //���� ȸ������ ����
	public void mediRegister(String id) {
		
	}
	
	@GetMapping("mediLogin") //�α��� ������
	public String mediLogin() {
		return "am/medi/mediLogin";
	}
	
	@PostMapping("mediLogin") //���� �α��� Ȯ��
	public void loginChk() {
		
	}
	@GetMapping("mediSearchIdPw") //���̵�/��й�ȣ ã�� ������
	public String SearchIdPw() {
		return "am/medi/mediSearchIdPw";
	}
	
	//�������� ����
	@GetMapping("mediInfo") // ���� �������� ������
	//public String info(@RequestParam String id, Model model) {
	public String info(String id, Model model) {
		model.addAttribute("info", ms.getMedi(id));
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
