package com.care.am.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.am.common.LoginSession;
import com.care.am.dto.customerDTO;
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
	public void mediRegister(mediDTO dto, HttpServletRequest req, HttpServletResponse res) {
		String[] addr = req.getParameterValues("mAddr");
		String ad ="";
		for(String a :addr) {
			ad+= a+"/";
		}
		addr = ad.split("/");
		
		String msg = ms.mediRegister(dto,req.getParameterValues("mAddr"));
		res.setContentType("text/html; charset=utf-8");
	    PrintWriter out = null;
		try {
			out = res.getWriter();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	    out.print( msg );
		
	}

	@GetMapping("mediLogin") // 로그인 페이지
	public String mediLogin() {
		return "am/medi/mediLogin";
	}

	@PostMapping("mediLogin") // 병원 로그인 확인
	public String loginChk(HttpSession session, 
								@RequestParam String id, 
								@RequestParam String pw,
								@RequestParam(required = false, defaultValue = "off") String autoLogin, 
								RedirectAttributes rs,
								HttpServletResponse res) throws Exception {
		int result = ms.logChk(id, pw);
		if (result == 0) {
			rs.addAttribute("id", id);
			rs.addAttribute("autoLogin", autoLogin);
			return "redirect:successMLogin";
		}else {
			res.setContentType("text/html; charset=UTF-8");
            PrintWriter out = null;
            try {
                out = res.getWriter();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            out.println("<script>alert('아이디나 비밀번호가 틀렸습니다.'); history.back();</script>");
            out.flush();
            return "redirect:mediLogin";
		}
	}

	@RequestMapping("successMLogin") // 로그인성공
	public String sucessMLogin(@RequestParam String id, 
								@RequestParam String autoLogin, 
								HttpSession session,
								HttpServletResponse res) {
		System.out.println("autologin:"+autoLogin); 
		if (autoLogin.equals("on")) { // 자동로그인 체크하면 쿠키생성
			int limitTime = 60 * 60 * 24 * 90; // 세달
			Cookie loginCookie = new Cookie("loginCookie", session.getId());
			System.out.println(session.getId());
			loginCookie.setPath("/"); // 경로를 최상위로 두어 모든곳에서 다 쓸수있게
			loginCookie.setMaxAge(limitTime);
			res.addCookie(loginCookie);
			ms.keepLogin(session.getId(), id);
		}
		session.setAttribute(LoginSession.mLOGIN,id); // 체크안했으면 그냥 세션만 만들어줘
		System.out.println("세션값"+LoginSession.mLOGIN);
		System.out.println("세션값22:"+session.getAttribute(LoginSession.mLOGIN));
		
		return "redirect:reservationState?id="+id;
	}

	@GetMapping("mediSearchIdPw") // 아이디/비밀번호 찾기 페이지
	public String SearchIdPw() {
		return "am/medi/mediSearchIdPw";
	}
	@RequestMapping("mediSearchId") // 병원 아이디 찾기
	public String mediSearchId(@RequestParam String inputName, 
								@RequestParam String inputTel,
								Model model, HttpServletResponse res) {
		String mId = ms.mediSearchId(inputName, inputTel);
		if(mId.length() >=1) {
			model.addAttribute("id",mId);
			return "am/medi/mediSearchId";
		}else {
			PrintWriter out = null;
            try {
                out = res.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.println("<script>alert('정보가 일치하지 않습니다.');</script>");
            out.flush();
            return "am/medi/mediSearchIdPw";
		}
	}
	
	@RequestMapping("mediSearchPw") //비밀번호 찾기
	public String mediSearchPw(@RequestParam String inputId, 
								@RequestParam String inputName,
								@RequestParam String inputTel,
								Model model,HttpServletResponse res) {
		mediDTO dto = ms.mediSearchPw(inputId,inputName,inputTel);
		if(dto !=null) {
			model.addAttribute("id", dto.getmId());
			return "redirect:/mediNewPwd";
		}else {
			PrintWriter out = null;
            try {
                out = res.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.println("<script>alert('정보가 일치하지 않습니다.');</script>");
            out.flush();
            return "am/medi/mediSearchIdPw";
		}
	}
	
	@GetMapping("mediNewPwd") // 비밀번호 재설정
	public String mediNewPwd(String id,Model model) {
		model.addAttribute("id", id);
		return "am/medi/mediNewPwd";
	}
	@PostMapping("mediNewPwd")
	public void mediNewPwd(@RequestParam String id, 
						@RequestParam String newPw,
						HttpServletResponse res) {
		String msg="";
		msg = ms.mediNewPwd(newPw, id);
		res.setContentType("text/html; charset=utf-8");
	    PrintWriter out = null;
		try {
			out = res.getWriter();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	    out.print( msg );	
	}
	


	// 개인정보 관련
	@GetMapping("mediInfo") // 병원 개인정보 페이지
	public String info(@RequestParam String id, Model model) {
		model.addAttribute("info", ms.getMedi(id));
		
		return "am/medi/mediInfo";
	}
	
	@GetMapping("mediPwdChk") //비밀번호 확인페이지
	public String mediPwdChk(@RequestParam String id) {
		return "am/medi/mediPwdChk";
	}
	
	@PostMapping("mediPwdChk")//비밀번호 확인
	public void mediPwdChk(@RequestParam String id, @RequestParam String pw, HttpServletResponse res) {
		String msg ="";
		msg = ms.mediPwdChk(id,pw);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out =null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(msg);
	}
	
	@GetMapping("mediPwdChg") //비밀번호 변경 페이지
	public String mediPwdChg(@RequestParam String id) {
		return "am/medi/mediPwdChg";
	}
	
	@PostMapping("mediPwdChg")
	public void mediPwdChg(mediDTO dto, HttpServletResponse res, @RequestParam String pw, @RequestParam String newPw) throws Exception{
		
		String msg="";
		
		System.out.println("con: "+dto.getmId());
		
		msg = ms.mediPwdChg(dto, pw, newPw);
		res.setContentType("text/html; charset=utf-8");
	    PrintWriter out = res.getWriter();
	    out.print( msg );
	}

	@GetMapping("mediModify") // 병원 개인정보 수정 페이지
	public String modify(@RequestParam String id, Model model) {
		model.addAttribute("info", ms.getMedi(id));
		
		return "am/medi/mediModify";
	}

	@PostMapping("mediModify") // 병원 개인정보 수정 적용
	public void modify(mediDTO dto, @RequestParam MultipartFile file, HttpServletResponse res, HttpServletRequest req, Model model) throws Exception {
	
		String[] addr = req.getParameterValues("mAddr");
		String ad ="";
		for(String a :addr) {
			ad+= a+"/";
		}
		addr = ad.split("/");
		System.out.println("file: "+file.getOriginalFilename());
		String msg = ms.mediModify(dto, file, req.getParameterValues("mAddr"));
		model.addAttribute("info", ms.getMedi(dto.getmId()));
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(msg);
		
	}
	@GetMapping("mediDelete")
	public String delete() {
		return "am/medi/mediDelete";
	}
	@PostMapping("mediDelete") // 병원 탈퇴
	public void delete(HttpSession session, @CookieValue(value="loginCookie",required=false)Cookie cookie,
			mediDTO dto,@RequestParam String pw, HttpServletResponse res) {
		String msg ="";
		if(cookie != null) {
			cookie.setMaxAge(0);
		}
		session.removeAttribute(LoginSession.mLOGIN);
		session.invalidate();
		msg= ms.mediDelete(dto,pw);
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
