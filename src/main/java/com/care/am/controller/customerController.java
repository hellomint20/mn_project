package com.care.am.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.am.common.LoginSession;
import com.care.am.dto.customerDTO;
import com.care.am.service.customer.customerService;

@Controller
public class customerController {
	
	@Autowired customerService cs;
	
	//�α��� ����
	@GetMapping("customerRegister") //�մ� ȸ������ ������
	public String register() {
		return "am/customer/customerRegister";
	}
	
	@PostMapping("customerRegister") //�մ� ȸ������ ����
	public void register(customerDTO dto, HttpServletResponse res) throws Exception{
	      String msg = cs.register(dto);
	      res.setContentType("text/html; charset=utf-8");
	      PrintWriter out = res.getWriter();
	      out.print( msg );
	}
	
	@GetMapping("customerLogin") //�α��� ������
	public String login() {
		return "am/customer/customerLogin";
	}
	
	@PostMapping("customerLogin") //�մ� �α��� Ȯ��
	public String loginChk(@RequestParam String id, 
						@RequestParam String pw,
						@RequestParam(required=false, defaultValue="off")String autoLogin,
						RedirectAttributes rs,
						HttpServletResponse res) throws Exception {
		
		int result = cs.logChk(id,pw);
		if(result == 1) {
			rs.addAttribute("id",id);
			rs.addAttribute("autoLogin",autoLogin); // consoleâ�� autoLogin ���� �����
			
			return "redirect:successLogin";
		}else {
			res.setContentType("text/html; charset=UTF-8");
            PrintWriter out = null;
            try {
                out = res.getWriter();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            out.println("<script>alert('���̵� ��й�ȣ�� Ʋ�Ƚ��ϴ�.'); history.back();</script>");
            out.flush();
            return "redirect:customerLogin";
		}
	}
	
	@RequestMapping("successLogin")
	public String successLogin(@RequestParam String id, 
								@RequestParam String autoLogin,
								HttpSession session,
								HttpServletResponse res) {
		
		System.out.println("autologin:"+autoLogin); 
		if(autoLogin.equals("on")) { //�ڵ��α��� üũ�ϸ� ��Ű����
			int limitTime = 60*60*24*90; //����
			Cookie loginCookie = new Cookie("loginCookie",session.getId());
			loginCookie.setPath("/"); // ��θ� �ֻ����� �ξ� �������� �� �����ְ�
			loginCookie.setMaxAge(limitTime);
			res.addCookie(loginCookie);
			cs.keepLogin(session.getId(),id);
			
		}
		session.setAttribute(LoginSession.cLOGIN, id); // üũ�������� �׳� ���Ǹ� �������
		return "redirect:/";
		
	}
	@GetMapping("customerSearchIdPw") //���̵�/��й�ȣ ã�� ������
	public String SearchIdPw() {
		return "am/customer/customerSearchIdPw";
	}
	
	//�մ����� ����
	@GetMapping("customerInfo") //�մ� �������� ������
	public String info(@RequestParam String id,Model model) {
		customerDTO dto = cs.getCustomerInfo(id);
		model.addAttribute("dto", dto);
		
		return "am/customer/customerInfo";
	}
	
	@GetMapping("customerPwdChk") // ��й�ȣ Ȯ��������
	public String customerPwdChk(@RequestParam String id) {
		return "am/customer/customerPwdChk";
	}
	
	@PostMapping("customerPwdChk") // ��й�ȣ Ȯ��
	public void customerPwdChk(@RequestParam String id,@RequestParam String pw, HttpServletResponse res) {
		String msg ="";
		msg= cs.customerPwdChk(id,pw);
		res.setContentType("text/html; charset=utf-8");
	    PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    out.print( msg );
	}
	
	@GetMapping("customerPwdChg") // ��й�ȣ ���� ������
	public String customerPwdChg(@RequestParam String id) {
		return "am/customer/customerPwdChg";
	}

	@PostMapping("customerPwdChg") // ��й�ȣ ����
	public void customerPwdChg(customerDTO dto, HttpServletResponse res,
								@RequestParam String pw,
								@RequestParam String newPw) throws Exception {
		String msg="";
		msg = cs.customerPwdChg(dto,pw,newPw);
		res.setContentType("text/html; charset=utf-8");
	    PrintWriter out = res.getWriter();
	    out.print( msg );
		
	}
	
	@GetMapping("customerModify") //�մ� �������� ���� ������
	public String modify(@RequestParam String id,Model model) {
		customerDTO dto = cs.getCustomerInfo(id);
		model.addAttribute("dto", dto);
		return "am/customer/customerModify";
	}
	
	@PostMapping("customerModify") //�մ� �������� ���� ����
	public void modify(customerDTO dto, HttpServletResponse res ){
			String msg="";
			msg = cs.customerModify(dto);
		    res.setContentType("text/html; charset=utf-8");
		    PrintWriter out = null;
		    try {
				out = res.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
		    out.print( msg );
	}
	
	@GetMapping("customerDelete") //�մ� Ż�� ������
	public String delete() {
		return "am/customer/customerDelete";
	}
	
	@PostMapping("customerDelete") //�մ� Ż��
	public void delete(HttpSession session, @CookieValue(value="loginCookie",required=false)Cookie cookie,
			customerDTO dto,@RequestParam String pw, HttpServletResponse res) {
		String msg ="";
		if(cookie != null) {
			cookie.setMaxAge(0);
		}
		session.removeAttribute(LoginSession.cLOGIN);
		session.invalidate();
		msg= cs.customerDelete(dto,pw);
		res.setContentType("text/html; charset=utf-8");
	    PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    out.print( msg );
	}
}