package com.care.am.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.api.plus.PlusOperations;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.am.common.LoginSession;
import com.care.am.dto.customerDTO;
import com.care.am.naver.NaverLoginBO;
import com.care.am.service.customer.customerService;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.care.am.service.loginLogic.loginLogicService;

@Controller
public class customerController{
	
	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;
	@Autowired
	private OAuth2Parameters googleOAuth2Parameters;

	@Autowired customerService cs;
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	@Autowired loginLogicService lls;
	
	
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
	
	
	@RequestMapping(value = "customerLogin", method = {RequestMethod.GET, RequestMethod.POST}) //�α��� ������
	public String login(Model model, HttpSession session) {
		/*���̹�*/
		String naverAuthUrl = naverLoginBO.getAuthorizational(session);
		model.addAttribute("url", naverAuthUrl);
		/* ���� */
		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		String url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
		System.out.println("����:" + url);
		model.addAttribute("google_url", url);
		
		return "am/customer/customerLogin";
	}
	
	@RequestMapping(value= "/navercallback", method= {RequestMethod.GET, RequestMethod.POST}) //���̹� �α��� �ݹ�
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session) throws Exception {
		System.out.println("naverCallback!");
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		apiResult = naverLoginBO.getUserProfile(oauthToken);
		model.addAttribute("result", apiResult);
		customerDTO dto = cs.naverLogin(apiResult);
		session.setAttribute(LoginSession.cLOGIN, dto.getcId());
        return "am/customer/naverLoginSuccess";

	}
	// ���� Callbackȣ�� �޼ҵ�
	@RequestMapping(value = "/googlecallback", method = { RequestMethod.GET, RequestMethod.POST })
	public String googlecallback(HttpServletRequest request, HttpServletResponse response, HttpSession session,
									Model model, @RequestParam String code)throws IOException {
		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
        AccessGrant accessGrant = oauthOperations.exchangeForAccess(code , googleOAuth2Parameters.getRedirectUri(), null);
        String accessToken = accessGrant.getAccessToken();
        Long expireTime = accessGrant.getExpireTime();
        
        if (expireTime != null && expireTime < System.currentTimeMillis()) {
            accessToken = accessGrant.getRefreshToken();
            System.out.printf("accessToken is expired. refresh token = {}", accessToken);
        }
        Connection<Google> connection = googleConnectionFactory.createConnection(accessGrant);
        Google google = connection == null ? new GoogleTemplate(accessToken) : connection.getApi();
        
        PlusOperations plusOperations = google.plusOperations();
        Person profile = plusOperations.getGoogleProfile();
        System.out.println("������������"+profile);
//        customerDTO dto = cs.googleLogin(profile); 


		return "am/customer/googleSuccess";
	}

	@PostMapping("cusloginChk") //�մ� �α��� Ȯ��
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
	
	@RequestMapping("successLogin") //�α��μ�����
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
		
		System.out.println("���ǰ�"+LoginSession.cLOGIN);
		System.out.println("���ǰ�:22"+session.getAttribute(LoginSession.cLOGIN));
		return "redirect:/";
		
	}
	@GetMapping("customerSearchIdPw") //���̵�/��й�ȣ ã�� ������
	public String searchIdPw() {
		return "am/customer/customerSearchIdPw";
	}
	
	
	@RequestMapping("customerSearchId") // ���̵�ã��
	public String customerSearchId( @RequestParam String inputName, 
									@RequestParam String inputEmail,
						 Model model,HttpServletResponse res) {
		String cId = cs.customerSearchId(inputName, inputEmail);
		if(cId.length() >= 1) {
			model.addAttribute("id",cId);
			return "am/customer/customerSearchId";
		}
		else {
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
				return "redirect:/customerSearchPw/"+"mong_jini@naver.com"+"/"+content+"/";
			}
			System.out.println(tempPwd);
		}
		return "redirect:/customerSearchIdPw";
		
	}
	
	@GetMapping("customerSearchPw")
	public String customerSearchPw() {
		return "am/customer/customerSearchPw";
	}
	
	
	//�մ����� ����
	@GetMapping("customerInfo") //�մ� �������� ������
	public String info(@RequestParam String id,Model model) {
		customerDTO dto = cs.getCustomerInfo(id);
		model.addAttribute("dto", dto);
		
		return "am/customer/customerInfo";
	}
	
	@GetMapping("customerPwdChk") // ��й�ȣ Ȯ��������
	public String customerPwdChk(@RequestParam String id,Model model) {
		customerDTO dto = cs.getCustomerInfo(id);
		model.addAttribute("dto", dto);
		
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
	public String delete(@RequestParam String id,Model model) {
		customerDTO dto = cs.getCustomerInfo(id);
		model.addAttribute("dto", dto);
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
	
	
	
	
	
