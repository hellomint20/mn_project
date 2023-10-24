package com.care.am.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.am.common.LoginSession;
import com.care.am.service.customer.customerService;
import com.mysql.cj.Session;

@Controller
public class commonController {
	
	@Autowired customerService cs;
	
	@GetMapping("/") //메인 홈페이지
	public String main() {
		return "am/common/main";
	}

	//css 확인 때문에 만듦 & 삭제 예정	
//	@GetMapping("/main2") //메인 홈페이지2
//	public String main2() {
//		return "am/common/main2";
//	}
	
	@GetMapping("mediSearch") //병원 찾기 페이지
	public String mediSearch() {
		return "am/common/mediSearch";
	}
	
	@RequestMapping("logout") //로그아웃
	public String logout(HttpSession session,
			@CookieValue(value="loginCookie", required=false)Cookie cookie,
			HttpServletResponse res) {
		System.out.println("logout: "+ session.getAttribute(LoginSession.LOGIN));
		
		if(cookie != null) {
			cookie.setMaxAge(0);
			cookie.setPath("/");
			res.addCookie(cookie);
			cs.keepLogin("nan", (String)session.getAttribute(LoginSession.LOGIN));
		}
		session.invalidate();
		return "redirect:/";
	}


}
