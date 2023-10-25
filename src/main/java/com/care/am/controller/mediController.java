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

	// 로그인 관련
	@GetMapping("mediRegister") // 병원 회원가입
	public String mediRegister() {
		return "am/medi/mediRegister";
	}

	@PostMapping("mediRegister") // 병원 회원가입 적용
	public void mediRegister(mediDTO dto, HttpServletResponse res) {

	}

	@GetMapping("mediLogin") // 로그인 페이지
	public String mediLogin() {
		return "am/medi/mediLogin";
	}

	@PostMapping("mediLogin") // 병원 로그인 확인
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

		if (autoLogin.equals("on")) { // 자동로그인 체크하면 쿠키생성
			
			int limitTime = 60 * 60 * 24 * 90; // 세달
			Cookie loginCookie = new Cookie("loginCookie", session.getId());
			
			loginCookie.setPath("/"); // 경로를 최상위로 두어 모든곳에서 다 쓸수있게
			loginCookie.setMaxAge(limitTime);
			res.addCookie(loginCookie);
			ms.keepLogin(session.getId(), id);
		}
		session.setAttribute(LoginSession.mLOGIN, id); // 체크안했으면 그냥 세션만 만들어줘
		System.out.println(session.getAttribute(LoginSession.mLOGIN));
		return "redirect:reservationState";
	}

	@GetMapping("mediSearchIdPw") // 아이디/비밀번호 찾기 페이지
	public String SearchIdPw() {
		return "am/medi/mediSearchIdPw";
	}

	// 개인정보 관련
	@GetMapping("mediInfo") // 병원 개인정보 페이지
	public String info() {
		return "am/medi/mediInfo";
	}

	@GetMapping("mediModify") // 병원 개인정보 수정 페이지
	public String modify() {
		return "am/medi/mediModify";
	}

	@PostMapping("mediModify") // 병원 개인정보 수정 적용
	public void modify(String id) {

	}

	@PostMapping("mediDelete") // 병원 탈퇴
	public void delete() {

	}
}
