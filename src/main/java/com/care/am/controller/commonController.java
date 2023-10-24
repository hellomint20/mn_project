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
	
	@GetMapping("/") //메인 홈페이지
	public String main() {
		return "am/common/main";
	}
<<<<<<< HEAD
	
	//css 확인 때문에 만듦 & 삭제 예정
	@GetMapping("/main2") //메인 홈페이지2
	public String main2() {
		return "am/common/main2";
	}
=======
	//css 확인 때문에 만듦 & 삭제 예정	
//	@GetMapping("/main2") //메인 홈페이지2
//	public String main2() {
//		return "am/common/main2";
//	}
>>>>>>> 4d83799524f19ab992fa228392b837c676ee7cba
	
	@GetMapping("mediSearch") //병원 찾기 페이지
	public String mediSearch() {
		return "am/common/mediSearch";
	}
	
	@RequestMapping("logout") //로그아웃
	public String logout(HttpSession session) {
<<<<<<< HEAD
		System.out.println("logout: "+ LoginSession.LOGIN);
=======
>>>>>>> 4d83799524f19ab992fa228392b837c676ee7cba
		session.invalidate();
		return "am/common/main";
	}


}
