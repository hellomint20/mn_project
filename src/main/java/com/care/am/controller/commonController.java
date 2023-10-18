package com.care.am.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class commonController {
	
	@GetMapping("/") //���� Ȩ������
	public String main() {
		return "am/common/main";
	}
	//css Ȯ�� ������ ���� & ���� ����
	@GetMapping("/main2") //���� Ȩ������2
	public String main2() {
		return "am/common/main2";
	}
	
	@GetMapping("mediSearch") //���� ã�� ������
	public String mediSearch() {
		return "am/common/mediSearch";
	}
	
	@PostMapping("logout") //�α׾ƿ�
	public String logout() {
		return "am/common/main";
	}
	
	@GetMapping("reservationPage")
	public String reservationPage() {
		return "am/reservation/reservationPage";
	}
	

}
