package com.care.am.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.care.am.common.LoginSession;
import com.care.am.service.customer.customerService;
import com.care.am.service.medi.mediService;

@Controller
public class commonController {
	
	@Autowired customerService cs;
	@Autowired mediService ms;
	@Autowired JavaMailSender mailSender;
	
	@GetMapping("/") //메인 홈페이지
	public String main() {
		return "am/common/main";
	}

	@GetMapping("mediSearch") //병원 찾기 페이지
	public String mediSearch() {
		return "am/common/mediSearch";
	}
	
	@RequestMapping("logout") //로그아웃
	public String logout(HttpSession session, 
						@CookieValue(value="loginCookie",required=false)Cookie cookie,
						HttpServletResponse res){
		if(cookie != null) {
			cookie.setMaxAge(0);
			cookie.setPath("/");
			res.addCookie(cookie);
			cs.keepLogin("nan", (String)session.getAttribute(LoginSession.cLOGIN));
			ms.keepLogin("nan", (String)session.getAttribute(LoginSession.mLOGIN));
		}	
		session.removeAttribute(LoginSession.cLOGIN);
		session.removeAttribute(LoginSession.mLOGIN);
		session.invalidate();
	
		return "redirect:/";
	}
}
