package com.care.am.controller;

import javax.servlet.http.HttpSession;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
=======
>>>>>>> 4d83799524f19ab992fa228392b837c676ee7cba
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.care.am.common.LoginSession;
import com.care.am.service.customer.customerService;

@Controller
public class commonController {
	
	@Autowired customerService cs;
	
	@GetMapping("/") //���� Ȩ������
	public String main() {
		return "am/common/main";
	}
<<<<<<< HEAD
	
	//css Ȯ�� ������ ���� & ���� ����
	@GetMapping("/main2") //���� Ȩ������2
	public String main2() {
		return "am/common/main2";
	}
=======
	//css Ȯ�� ������ ���� & ���� ����	
//	@GetMapping("/main2") //���� Ȩ������2
//	public String main2() {
//		return "am/common/main2";
//	}
>>>>>>> 4d83799524f19ab992fa228392b837c676ee7cba
	
	@GetMapping("mediSearch") //���� ã�� ������
	public String mediSearch() {
		return "am/common/mediSearch";
	}
	
	@RequestMapping("logout") //�α׾ƿ�
	public String logout(HttpSession session) {
<<<<<<< HEAD
		System.out.println("logout: "+ LoginSession.LOGIN);
=======
>>>>>>> 4d83799524f19ab992fa228392b837c676ee7cba
		session.invalidate();
		return "am/common/main";
	}


}
