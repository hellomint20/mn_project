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
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) { // 네이버로그인 호출
		this.naverLoginBO = naverLoginBO;
	}

	// 로그인 관련
	@GetMapping("customerRegister") // 보호자 회원가입 페이지
	public String register() {
		return "am/customer/customerRegister";
	}

	@PostMapping("/idCheck") // 회원가입시 아이디 중복확인
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

	@PostMapping("customerRegister") // 손님 회원가입 적용
	public void register(customerDTO dto, HttpServletResponse res) throws Exception {
		String msg = cs.register(dto);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(msg);
	}

	@RequestMapping(value = "customerLogin", method = { RequestMethod.GET, RequestMethod.POST }) // 로그인 페이지
	public String login(Model model, HttpSession session) {
		/* 네이버 */
		String naverAuthUrl = naverLoginBO.getAuthorizational(session);
		model.addAttribute("url", naverAuthUrl);

		return "am/customer/customerLogin";
	}

	@RequestMapping(value = "/navercallback", method = { RequestMethod.GET, RequestMethod.POST }) // 네이버 로그인 콜백
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
			throws Exception {
		System.out.println("naverCallback!");
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		apiResult = naverLoginBO.getUserProfile(oauthToken);
		model.addAttribute("result", apiResult);
		customerDTO dto = cs.naverLogin(apiResult);
		System.out.println("디티오겟아이디:" + dto.getcId());
		session.setAttribute(LoginSession.cLOGIN, dto.getcId());
		session.setAttribute(LoginSession.sLOGIN, dto.getcId());
		return "am/customer/naverLoginSuccess";

	}

	@PostMapping("cusloginChk") // 보호자 로그인 확인
	public String loginChk(@RequestParam String id, @RequestParam String pw,
			@RequestParam(required = false, defaultValue = "off") String autoLogin, RedirectAttributes rs,
			HttpServletResponse res) throws Exception {
		int result = cs.logChk(id, pw);
		if (result == 1) {
			rs.addAttribute("id", id);
			rs.addAttribute("autoLogin", autoLogin); // console창에 autoLogin 상태 띄어줌

			return "redirect:successLogin";
		} else {
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = null;
			try {
				out = res.getWriter();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			out.println("<script>alert('아이디나 비밀번호가 틀렸습니다.'); history.back();</script>");
			out.flush();
			return "redirect:customerLogin";
		}
	}

	@RequestMapping("successLogin") // 로그인성공시
	public String successLogin(@RequestParam String id, @RequestParam String autoLogin, HttpSession session,
			HttpServletResponse res) {

		System.out.println("autologin:" + autoLogin);
		if (autoLogin.equals("on")) { // 자동로그인 체크하면 쿠키생성
			int limitTime = 60 * 60 * 24 * 90; // 세달
			Cookie loginCookie = new Cookie("loginCookie", session.getId());
			loginCookie.setPath("/"); // 경로를 최상위로 두어 모든곳에서 다 쓸수있게
			loginCookie.setMaxAge(limitTime);
			res.addCookie(loginCookie);
			cs.keepLogin(session.getId(), id);
		}
		session.setAttribute(LoginSession.cLOGIN, id); // 체크안했으면 그냥 세션만 만들어줘

		System.out.println("세션값" + LoginSession.cLOGIN);
		System.out.println("세션값:22" + session.getAttribute(LoginSession.cLOGIN));
		return "redirect:/";

	}

	@GetMapping("customerSearchIdPw") // 아이디/비밀번호 찾기 페이지
	public String searchIdPw() {
		return "am/customer/customerSearchIdPw";
	}

	@RequestMapping("customerSearchId") // 아이디찾기
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
			out.println("<script>alert('정보가 일치하지 않습니다.');</script>");
			out.flush();
			return "am/customer/customerSearchIdPw";
		}
	}

	@PostMapping("customerSearchPw") // 비밀번호 찾기
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

	@GetMapping("customerSearchPw") // 보호자 비밀번호 찾기
	public String customerSearchPw() {
		return "am/customer/customerSearchPw";
	}

	// 보호자정보 관련
	@GetMapping("customerInfo") // 보호자 개인정보 페이지
	public String info(@RequestParam String id, HttpServletRequest req, Model model, HttpSession session) {
		customerDTO dto = cs.getCustomerInfo(id);
		model.addAttribute("dto", dto);

		// 현재 요청의 모든 쿠키 가져오기
		Cookie[] cookies = req.getCookies();
		String cId = session.getAttribute(LoginSession.cLOGIN).toString();
		System.out.println(cookies);

		// 가져온 쿠키를 기반으로 최근에 본 병원 목록 생성
		List<String> recentlyViewed = new ArrayList<>();
		List<Map<String, String>> getViewList = new ArrayList<Map<String, String>>();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().startsWith("cookie")) {
					// "cookie"로 시작하는 쿠키의 값을 추출하여 목록에 추가
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

	@GetMapping("customerPwdChk") // 비밀번호 확인페이지
	public String customerPwdChk(@RequestParam String id, Model model) {
		customerDTO dto = cs.getCustomerInfo(id);
		model.addAttribute("dto", dto);

		return "am/customer/customerPwdChk";
	}

	@PostMapping("customerPwdChk") // 비밀번호 확인
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

	@GetMapping("customerPwdChg") // 비밀번호 변경 페이지
	public String customerPwdChg(@RequestParam String id) {
		return "am/customer/customerPwdChg";
	}

	@PostMapping("customerPwdChg") // 비밀번호 변경
	public void customerPwdChg(customerDTO dto, HttpServletResponse res, @RequestParam String pw,
			@RequestParam String newPw) throws Exception {
		String msg = "";
		msg = cs.customerPwdChg(dto, pw, newPw);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(msg);

	}

	@GetMapping("customerModify") // 보호자 개인정보 수정 페이지
	public String modify(@RequestParam String id, Model model) {
		customerDTO dto = cs.getCustomerInfo(id);
		model.addAttribute("dto", dto);
		return "am/customer/customerModify";
	}

	@PostMapping("customerModify") // 보호자 개인정보 수정 적용
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

	@GetMapping("customerDelete") // 보호자 탈퇴 페이지
	public String delete(@RequestParam String id, Model model) {
		customerDTO dto = cs.getCustomerInfo(id);
		model.addAttribute("dto", dto);
		return "am/customer/customerDelete";
	}

	@PostMapping("customerDelete") // 보호자 탈퇴
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
	@PostMapping("customer/recentlyView") // 최근 본 병원
	public void recentlyView(@RequestBody String mediId, HttpSession session,
			@CookieValue(value = "recentlyCookie", required = false) Cookie cookie, HttpServletResponse res,
			HttpServletRequest req) {
		String cId = session.getAttribute(LoginSession.cLOGIN).toString();
		int limitTime = 60 * 60 * 24; // 24시간
		SimpleDateFormat date = new SimpleDateFormat("HHmmss"); // 현재시간 포맷해서 가져오기
		String fd = date.format(new Date());
		Cookie viewCookie = new Cookie("cookie" + fd, cId + "/" + mediId); // 쿠키명에 현재시간을 넣어서 생성

		viewCookie.setPath("/"); // 경로를 최상위로 두어 모든곳에서 다 쓸수있게
		viewCookie.setMaxAge(limitTime);
		res.addCookie(viewCookie);
		cs.addrecentlyView(viewCookie.getValue());

	}
}
