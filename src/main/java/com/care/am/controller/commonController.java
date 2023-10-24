package com.care.am.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.care.am.common.LoginSession;
import com.care.am.service.customer.customerService;
import com.care.am.service.medi.mediService;

@Controller
public class commonController {
	
	@Autowired customerService cs;
	@Autowired mediService ms;
	
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
						@CookieValue(value="loginCookie",required=false)Cookie cookie,
						HttpServletResponse res){
		if(cookie != null) {
			cookie.setMaxAge(0);
			cookie.setPath("/");
			res.addCookie(cookie);
			cs.keepLogin("nan", (String)session.getAttribute(LoginSession.cLOGIN));
			ms.keepLogin("nan", (String)session.getAttribute(LoginSession.mLOGIN));
			System.out.println("로그아웃완료인뎅");
		}	
		session.removeAttribute(LoginSession.cLOGIN);
		session.removeAttribute(LoginSession.mLOGIN);
		session.invalidate();
	
		return "redirect:/";
	}

}