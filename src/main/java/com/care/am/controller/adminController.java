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
	
	@RequestMapping(value = "adminLogin", method = {RequestMethod.GET, RequestMethod.POST}) //�α��� ������
	public String adminLogin(Model model) {
		
		return "am/admin/adminLogin";
	}
	
//	@PostMapping("adminLoginChk") //���� �α��� Ȯ��
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
//            out.println("<script>alert('���̵� ��й�ȣ�� Ʋ�Ƚ��ϴ�.'); history.back();</script>");
//            out.flush();
//            return "redirect:customerLogin";
//		}
//	}
//	
//	@RequestMapping("adminSuccessLogin") //�α��μ�����
//	public String successLogin(@RequestParam String id, 
//								HttpSession session,
//								HttpServletResponse res) {
//		
//		session.setAttribute(LoginSession.cLOGIN, id); // üũ�������� �׳� ���Ǹ� �������
//		
//		System.out.println("���ǰ�"+LoginSession.cLOGIN);
//		System.out.println("���ǰ�:22"+session.getAttribute(LoginSession.cLOGIN));
//		return "redirect:/";
//		
//	}
	
	@GetMapping("adminMain") 
	public String adminMain() {
		
		return "am/admin/adminMain";
	}
	
}
