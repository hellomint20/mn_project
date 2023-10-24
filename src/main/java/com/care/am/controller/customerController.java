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
			rs.addAttribute("autoLogin",autoLogin); // consoleâ�� autoLogin ���� �����
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
		if(autoLogin.equals("on")) { //�ڵ��α��� üũ�ϸ� ��Ű����

			int limitTime = 60*60*24*90; //����
			
			Cookie loginCookie = new Cookie("loginCookie",session.getId());
			loginCookie.setPath("/"); // ��θ� �ֻ����� �ξ� �������� �� �����ְ�
			loginCookie.setMaxAge(limitTime);
			res.addCookie(loginCookie);
			
			cs.keepLogin(session.getId(), id);
			System.out.println("�ڵ��α��� ��Ű����");
			}
		}
		session.setAttribute(LoginSession.LOGIN, id); // üũ�������� �׳� ���Ǹ� �������
		System.out.println(LoginSession.LOGIN);
		return "redirect:/";
	}
	
	@GetMapping("customerSearchIdPw") // ���̵�/��й�ȣ ã�� ������
	public String SearchIdPw() {
		return "am/customer/customerSearchIdPw";
	}
	
	//�մ����� ����
	@GetMapping("customerInfo") //�մ� �������� ������
	public String info() {
		return "am/customer/customerInfo";
	}
	
	@GetMapping("customerModify") //�մ� �������� ���� ������
	public String modify() {
		return "am/customer/customerModify";
	}
	
	@PostMapping("customerModify") //�մ� �������� ���� ����
	public void modify(String id) {
		
	}
	
	@GetMapping("customerDelete") //�մ� Ż�� ������
	public String delete() {
		return "am/customer/customerDelete";
	}
	
	@PostMapping("customerDelete") //�մ� Ż��
	public void delete(String id, String pw) {
		// �Ű����� ���Ƿ� �־� ��
	}
}
