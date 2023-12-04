package com.care.am.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.am.common.LoginSession;
import com.care.am.dto.customerDTO;
import com.care.am.naver.NaverLoginBO;
import com.care.am.service.customer.customerService;
import com.care.am.service.loginLogic.loginLogicService;
import com.github.scribejava.core.model.OAuth2AccessToken;

@Controller
public class customerController {
	@Autowired
	customerService cs;

	@Autowired
	loginLogicService lls;

	private NaverLoginBO naverLoginBO;
	private String apiResult = null;

	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) { // ���̹��α��� ȣ��
		this.naverLoginBO = naverLoginBO;
	}

	// �α��� ����
	@GetMapping("customerRegister") // ��ȣ�� ȸ������ ������
	public String register() {
		return "am/customer/customerRegister";
	}

	@PostMapping("/idCheck") // ȸ�����Խ� ���̵� �ߺ�Ȯ��
	@ResponseBody
	public ResponseEntity<Boolean> idCheck(String id) {
		System.out.println("ConfirmId.........");
		System.out.println("id : " + id);
		boolean result = true;

		if (id.trim().isEmpty()) {
			result = false;
		} else {
			if (cs.idCheck(id)) {
				result = false;
			} else {
				result = true;
			}
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("customerRegister") // �մ� ȸ������ ����
	public void register(customerDTO dto, HttpServletResponse res) throws Exception {
		String msg = cs.register(dto);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(msg);
	}

	@RequestMapping(value = "customerLogin", method = { RequestMethod.GET, RequestMethod.POST }) // �α��� ������
	public String login(Model model, HttpSession session) {
		/* ���̹� */
		String naverAuthUrl = naverLoginBO.getAuthorizational(session);
		model.addAttribute("url", naverAuthUrl);

		return "am/customer/customerLogin";
	}

	@RequestMapping(value = "/navercallback", method = { RequestMethod.GET, RequestMethod.POST }) // ���̹� �α��� �ݹ�
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
			throws Exception {
		System.out.println("naverCallback!");
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		apiResult = naverLoginBO.getUserProfile(oauthToken);
		model.addAttribute("result", apiResult);
		customerDTO dto = cs.naverLogin(apiResult);
		System.out.println("��Ƽ���پ��̵�:" + dto.getcId());
		session.setAttribute(LoginSession.cLOGIN, dto.getcId());
		session.setAttribute(LoginSession.sLOGIN, dto.getcId());
		return "am/customer/naverLoginSuccess";

	}

	@PostMapping("cusloginChk") // ��ȣ�� �α��� Ȯ��
	public String loginChk(@RequestParam String id, @RequestParam String pw,
			@RequestParam(required = false, defaultValue = "off") String autoLogin, RedirectAttributes rs,
			HttpServletResponse res) throws Exception {
		int result = cs.logChk(id, pw);
		if (result == 1) {
			rs.addAttribute("id", id);
			rs.addAttribute("autoLogin", autoLogin); // consoleâ�� autoLogin ���� �����

			return "redirect:successLogin";
		} else {
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

	@RequestMapping("successLogin") // �α��μ�����
	public String successLogin(@RequestParam String id, @RequestParam String autoLogin, HttpSession session,
			HttpServletResponse res) {

		System.out.println("autologin:" + autoLogin);
		if (autoLogin.equals("on")) { // �ڵ��α��� üũ�ϸ� ��Ű����
			int limitTime = 60 * 60 * 24 * 90; // ����
			Cookie loginCookie = new Cookie("loginCookie", session.getId());
			loginCookie.setPath("/"); // ��θ� �ֻ����� �ξ� �������� �� �����ְ�
			loginCookie.setMaxAge(limitTime);
			res.addCookie(loginCookie);
			cs.keepLogin(session.getId(), id);
		}
		session.setAttribute(LoginSession.cLOGIN, id); // üũ�������� �׳� ���Ǹ� �������

		System.out.println("���ǰ�" + LoginSession.cLOGIN);
		System.out.println("���ǰ�:22" + session.getAttribute(LoginSession.cLOGIN));
		return "redirect:/";

	}

	@GetMapping("customerSearchIdPw") // ���̵�/��й�ȣ ã�� ������
	public String searchIdPw() {
		return "am/customer/customerSearchIdPw";
	}

	@RequestMapping("customerSearchId") // ���̵�ã��
	public String customerSearchId(@RequestParam String inputName, @RequestParam String inputEmail, Model model,
			HttpServletResponse res) {
		List<Map<String, String>> idList = cs.customerSearchId(inputName, inputEmail);
		if (idList.size() >= 1) {
			model.addAttribute("idList", idList);
			return "am/customer/customerSearchId";
		} else {
			PrintWriter out = null;
			try {
				out = res.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.println("<script>alert('������ ��ġ���� �ʽ��ϴ�.');</script>");
			out.flush();
			return "am/customer/customerSearchIdPw";
		}
	}

	@PostMapping("customerSearchPw") // ��й�ȣ ã��
	public String customerSearchPw(@RequestParam String inputId, 
								@RequestParam String inputName, 
								@RequestParam String inputTel,HttpServletResponse res) {
		
		customerDTO dto = cs.customerSearchPw(inputId,inputName,inputTel);
		String tempPwd ="";
		if(dto !=null) {
			tempPwd = cs.makeRandomPw();
			int result = cs.customerPwChg(tempPwd,dto);
			if(result ==1) {
				String toMail = dto.getcEmail();
				String content = tempPwd;
				return "redirect:/customerSearchPw/"+toMail+"/"+content+"/";
			}
			System.out.println(tempPwd);
		}
		return "redirect:/customerSearchIdPw";
		
	}

	@GetMapping("customerSearchPw") // ��ȣ�� ��й�ȣ ã��
	public String customerSearchPw() {
		return "am/customer/customerSearchPw";
	}

	// ��ȣ������ ����
	@GetMapping("customerInfo") // ��ȣ�� �������� ������
	public String info(@RequestParam String id, HttpServletRequest req, Model model, HttpSession session) {
		customerDTO dto = cs.getCustomerInfo(id);
		model.addAttribute("dto", dto);

		// ���� ��û�� ��� ��Ű ��������
		Cookie[] cookies = req.getCookies();
		String cId = session.getAttribute(LoginSession.cLOGIN).toString();
		System.out.println(cookies);

		// ������ ��Ű�� ������� �ֱٿ� �� ���� ��� ����
		List<String> recentlyViewed = new ArrayList<>();
		List<Map<String, String>> getViewList = new ArrayList<Map<String, String>>();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().startsWith("cookie")) {
					// "cookie"�� �����ϴ� ��Ű�� ���� �����Ͽ� ��Ͽ� �߰�
					recentlyViewed.add(cookie.getValue());
					// model.addAttribute("list",recentlyViewedHospitals);
				}
			}
			session.removeAttribute("recentlyViewList");
			getViewList = cs.getRecentlyView(recentlyViewed, cId);
			session.setAttribute("recentlyViewList", getViewList);
			// model.addAttribute("recentlyViewList", getViewList);
			

		} else {
			System.out.println("null");
		}

		return "am/customer/customerInfo";
	}

	@GetMapping("customerPwdChk") // ��й�ȣ Ȯ��������
	public String customerPwdChk(@RequestParam String id, Model model) {
		customerDTO dto = cs.getCustomerInfo(id);
		model.addAttribute("dto", dto);

		return "am/customer/customerPwdChk";
	}

	@PostMapping("customerPwdChk") // ��й�ȣ Ȯ��
	public void customerPwdChk(@RequestParam String id, @RequestParam String pw, HttpServletResponse res) {
		String msg = "";
		msg = cs.customerPwdChk(id, pw);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(msg);
	}

	@GetMapping("customerPwdChg") // ��й�ȣ ���� ������
	public String customerPwdChg(@RequestParam String id) {
		return "am/customer/customerPwdChg";
	}

	@PostMapping("customerPwdChg") // ��й�ȣ ����
	public void customerPwdChg(customerDTO dto, HttpServletResponse res, @RequestParam String pw,
			@RequestParam String newPw) throws Exception {
		String msg = "";
		msg = cs.customerPwdChg(dto, pw, newPw);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(msg);

	}

	@GetMapping("customerModify") // ��ȣ�� �������� ���� ������
	public String modify(@RequestParam String id, Model model) {
		customerDTO dto = cs.getCustomerInfo(id);
		model.addAttribute("dto", dto);
		return "am/customer/customerModify";
	}

	@PostMapping("customerModify") // ��ȣ�� �������� ���� ����
	public void modify(customerDTO dto, HttpServletResponse res) {
		String msg = "";
		msg = cs.customerModify(dto);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(msg);
	}

	@GetMapping("customerDelete") // ��ȣ�� Ż�� ������
	public String delete(@RequestParam String id, Model model) {
		customerDTO dto = cs.getCustomerInfo(id);
		model.addAttribute("dto", dto);
		return "am/customer/customerDelete";
	}

	@PostMapping("customerDelete") // ��ȣ�� Ż��
	public void delete(HttpSession session, @CookieValue(value = "loginCookie", required = false) Cookie cookie,
			customerDTO dto, @RequestParam String pw, HttpServletResponse res) {
		String msg = "";
		if (cookie != null) {
			cookie.setMaxAge(0);
		}
		session.removeAttribute(LoginSession.cLOGIN);
		session.invalidate();
		msg = cs.customerDelete(dto, pw);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(msg);
	}

	@ResponseBody
	@PostMapping("customer/recentlyView") // �ֱ� �� ����
	public void recentlyView(@RequestBody String mediId, HttpSession session,
			@CookieValue(value = "recentlyCookie", required = false) Cookie cookie, HttpServletResponse res,
			HttpServletRequest req) {
		String cId = session.getAttribute(LoginSession.cLOGIN).toString();
		int limitTime = 60 * 60 * 24; // 24�ð�
		SimpleDateFormat date = new SimpleDateFormat("HHmmss"); // ����ð� �����ؼ� ��������
		String fd = date.format(new Date());
		Cookie viewCookie = new Cookie("cookie" + fd, cId + "/" + mediId); // ��Ű�� ����ð��� �־ ����

		viewCookie.setPath("/"); // ��θ� �ֻ����� �ξ� �������� �� �����ְ�
		viewCookie.setMaxAge(limitTime);
		res.addCookie(viewCookie);
		cs.addrecentlyView(viewCookie.getValue());

	}
}
