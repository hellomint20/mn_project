package com.care.am.controller;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.am.common.LoginSession;
import com.care.am.dto.customerDTO;
import com.care.am.service.customer.customerService;

@Controller
public class customerController {
	
	@Autowired customerService cs;
	
	//로그인 관련
	@GetMapping("customerRegister") //손님 회원가입 페이지
	public String register() {
		return "am/customer/customerRegister";
	}
	
	@PostMapping("customerRegister") //손님 회원가입 적용
	public void register(customerDTO dto, HttpServletResponse res) throws Exception{
	      String msg = cs.register(dto);
	      res.setContentType("text/html; charset=utf-8");
	      PrintWriter out = res.getWriter();
	      out.print( msg );
	}
	
	@GetMapping("customerLogin") //로그인 페이지
	public String login() {
		return "am/customer/customerLogin";
	}
	
	@PostMapping("customerLogin") //손님 로그인 확인
	public String loginChk(HttpSession session, 
						@RequestParam String id, 
						@RequestParam String pw,
						@RequestParam(required=false, defaultValue="off")String autoLogin,
						RedirectAttributes rs,
						HttpServletResponse res) throws Exception {

		int result = cs.logChk(id,pw);
		if(result == 0) {
			rs.addAttribute("id",id);
			rs.addAttribute("autoLogin", autoLogin);
			rs.addAttribute("autoLogin",autoLogin); // console창에 autoLogin 상태 띄어줌
			return "redirect:successLogin";
		}
		return "redirect:customerLogin";
	}
	
	@RequestMapping("successLogin")
	public String successLogin(@RequestParam String id, 
								@RequestParam String autoLogin,
								HttpSession session,
								HttpServletResponse res) {
		
		System.out.println("autologin: " +autoLogin);
		
		if(autoLogin.equals("on")) {
		System.out.println("autologin:"+autoLogin); 
		if(autoLogin.equals("on")) { //자동로그인 체크하면 쿠키생성

			int limitTime = 60*60*24*90; //세달
			
			Cookie loginCookie = new Cookie("loginCookie",session.getId());
			loginCookie.setPath("/"); // 경로를 최상위로 두어 모든곳에서 다 쓸수있게
			loginCookie.setMaxAge(limitTime);
			res.addCookie(loginCookie);
			
			cs.keepLogin(session.getId(), id);
			System.out.println("자동로그인 쿠키생성");
			}
		}
		session.setAttribute(LoginSession.LOGIN, id); // 체크안했으면 그냥 세션만 만들어줘
		System.out.println(LoginSession.LOGIN);
		return "redirect:/";
	}
	
	@GetMapping("customerSearchIdPw") // 아이디/비밀번호 찾기 페이지
	public String SearchIdPw() {
		return "am/customer/customerSearchIdPw";
	}
	
	//손님정보 관련
	@GetMapping("customerInfo") //손님 개인정보 페이지
	public String info() {
		return "am/customer/customerInfo";
	}
	
	@GetMapping("customerModify") //손님 개인정보 수정 페이지
	public String modify() {
		return "am/customer/customerModify";
	}
	
	@PostMapping("customerModify") //손님 개인정보 수정 적용
	public void modify(String id) {
		
	}
	
	@GetMapping("customerDelete") //손님 탈퇴 페이지
	public String delete() {
		return "am/customer/customerDelete";
	}
	
	@PostMapping("customerDelete") //손님 탈퇴
	public void delete(String id, String pw) {
		// 매개변수 임의로 넣어 둠
	}
}
