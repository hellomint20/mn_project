package com.care.am.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.am.common.LoginSession;
import com.care.am.dto.mediDTO;
import com.care.am.service.medi.mediService;

@Controller
public class mediController {

	@Autowired	mediService ms;

	// �α��� ����
	@GetMapping("mediRegister") // ���� ȸ������
	public String mediRegister() {
		return "am/medi/mediRegister";
	}

	@PostMapping("mediRegister") // ���� ȸ������ ����
	public void mediRegister(mediDTO dto, HttpServletResponse res) {

	}

	@GetMapping("mediLogin") // �α��� ������
	public String mediLogin() {
		return "am/medi/mediLogin";
	}

	@PostMapping("mediLogin") // ���� �α��� Ȯ��
	public String loginChk(HttpSession session, @RequestParam String id, @RequestParam String pw,
			@RequestParam(required = false, defaultValue = "off") String autoLogin, RedirectAttributes rs,
			HttpServletResponse res) throws Exception {
		int result = ms.logChk(id, pw);
		if (result == 0) {
			rs.addAttribute("id", id);
			rs.addAttribute("autoLogin", autoLogin);
			return "redirect:successMLogin";
		}

		return "redirect:mediLogin";
	}

	@RequestMapping("successMLogin")
	public String sucessMLogin(@RequestParam String id, @RequestParam String autoLogin, HttpSession session,
			HttpServletResponse res) {

		if (autoLogin.equals("on")) { // �ڵ��α��� üũ�ϸ� ��Ű����
			
			int limitTime = 60 * 60 * 24 * 90; // ����
			Cookie loginCookie = new Cookie("loginCookie", session.getId());
			
			loginCookie.setPath("/"); // ��θ� �ֻ����� �ξ� �������� �� �����ְ�
			loginCookie.setMaxAge(limitTime);
			res.addCookie(loginCookie);
			ms.keepLogin(session.getId(), id);
		}
		session.setAttribute(LoginSession.mLOGIN, id); // üũ�������� �׳� ���Ǹ� �������
		System.out.println(session.getAttribute(LoginSession.mLOGIN));
		return "redirect:reservationState";
	}

	@GetMapping("mediSearchIdPw") // ���̵�/��й�ȣ ã�� ������
	public String SearchIdPw() {
		return "am/medi/mediSearchIdPw";
	}

	// �������� ����
	@GetMapping("mediInfo") // ���� �������� ������
	public String info() {
		return "am/medi/mediInfo";
	}

	@GetMapping("mediModify") // ���� �������� ���� ������
	public String modify() {
		return "am/medi/mediModify";
	}

	@PostMapping("mediModify") // ���� �������� ���� ����
	public void modify(String id) {

	}

	@PostMapping("mediDelete") // ���� Ż��
	public void delete() {

	}
}
