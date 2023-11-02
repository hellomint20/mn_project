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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.am.common.LoginSession;
import com.care.am.dto.customerDTO;
import com.care.am.dto.mediDTO;
import com.care.am.service.customer.customerService;
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
            return "redirect:mediLogin";
		}
	}

	@RequestMapping("successMLogin") // �α��μ���
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
	@RequestMapping("mediSearchId") // ���� ���̵� ã��
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
            out.println("<script>alert('������ ��ġ���� �ʽ��ϴ�.');</script>");
            out.flush();
            return "am/medi/mediSearchIdPw";
		}
	}
	
	@RequestMapping("mediSearchPw") //��й�ȣ ã��
	public String mediSearchPw(@RequestParam String inputId, 
								@RequestParam String inputName,
								@RequestParam String inputTel,
								Model model,HttpServletResponse res) {
		mediDTO dto = ms.mediSearchPw(inputId,inputName,inputTel);
		if(dto !=null) {
			model.addAttribute("id", dto.getmId());
			return "redirect:/mediPwChg";
		}else {
			PrintWriter out = null;
            try {
                out = res.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.println("<script>alert('������ ��ġ���� �ʽ��ϴ�.');</script>");
            out.flush();
            return "am/medi/mediSearchIdPw";
		}
	}
	
	@GetMapping("mediPwChg") // ��й�ȣ �缳��
	public String mediPwChg(String id,Model model) {
		model.addAttribute("id", id);
		return "am/medi/mediPwChg";
	}
	@PostMapping("mediPwChg")
	public void mediPwChg(@RequestParam String id, 
						@RequestParam String newPw,
						HttpServletResponse res) {
		String msg="";
		msg = ms.mediPwChg(newPw, id);
		res.setContentType("text/html; charset=utf-8");
	    PrintWriter out = null;
		try {
			out = res.getWriter();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	    out.print( msg );	
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
