package com.care.am.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.am.common.LoginSession;
import com.care.am.service.admin.adminService;

@Controller
public class adminController {
	
	@Autowired adminService as;
	
	@RequestMapping(value = "adminLogin", method = {RequestMethod.GET, RequestMethod.POST}) //로그인 페이지
	public String adminLogin(Model model) {
		
		return "am/admin/adminLogin";
	}
	
//	@PostMapping("adminLoginChk") //어드민 로그인 확인
//	public String loginChk(@RequestParam String id, 
//						@RequestParam String pw,
//						RedirectAttributes rs,
//						HttpServletResponse res) throws Exception {
//		int result = as.logChk(id,pw);
//		if(result == 1) {
//			rs.addAttribute("id",id);
//			
//			return "redirect:adminSuccessLogin";
//		}else {
//			res.setContentType("text/html; charset=UTF-8");
//            PrintWriter out = null;
//            try {
//                out = res.getWriter();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            out.println("<script>alert('아이디나 비밀번호가 틀렸습니다.'); history.back();</script>");
//            out.flush();
//            return "redirect:customerLogin";
//		}
//	}
//	
//	@RequestMapping("adminSuccessLogin") //로그인성공시
//	public String successLogin(@RequestParam String id, 
//								HttpSession session,
//								HttpServletResponse res) {
//		
//		session.setAttribute(LoginSession.cLOGIN, id); // 체크안했으면 그냥 세션만 만들어줘
//		
//		System.out.println("세션값"+LoginSession.cLOGIN);
//		System.out.println("세션값:22"+session.getAttribute(LoginSession.cLOGIN));
//		return "redirect:/";
//		
//	}
	
	@GetMapping("adminMain") 
	public String adminMain() {
		
		return "am/admin/adminMain";
	}
	
}
