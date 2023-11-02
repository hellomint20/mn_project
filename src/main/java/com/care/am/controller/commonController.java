package com.care.am.controller;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.care.am.common.LoginSession;
import com.care.am.service.customer.customerService;
import com.care.am.service.medi.mediService;

@Controller
public class commonController {
	
	@Autowired customerService cs;
	@Autowired mediService ms;
	@Autowired JavaMailSender mailSender;
	
	@GetMapping("/") //메인 홈페이지
	public String main() {
		return "am/common/main";
	}
	
	@GetMapping("/main2") //메인 홈페이지
	public void main2() throws Exception{
		
		String subject = "test 메일";
        String content = "test 메일 테스트 내용 = 되어라 ";
        String from = "tjgmlrla0213@gmail.com";
        String to = "shroad213@naver.com";
        
       final MimeMessagePreparator preparator = new MimeMessagePreparator() {
		
		@Override
		public void prepare(MimeMessage mimeMessage) throws Exception {
			final MimeMessageHelper mailHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			
			mailHelper.setFrom(from);
			mailHelper.setTo(to);
			mailHelper.setSubject(subject);
			mailHelper.setText(content, true);
		}
	};
		try {
			mailSender.send(preparator);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping("mediSearch") //병원 찾기 페이지
	public String mediSearch() {
		return "am/common/mediSearch";
	}
	
	@RequestMapping("logout") //로그아웃
	public String logout(HttpSession session, 
						@CookieValue(value="loginCookie",required=false)Cookie cookie,
						HttpServletResponse res){
		if(cookie != null) {
			cookie.setMaxAge(0);
			cookie.setPath("/");
			res.addCookie(cookie);
			cs.keepLogin("nan", (String)session.getAttribute(LoginSession.cLOGIN));
			ms.keepLogin("nan", (String)session.getAttribute(LoginSession.mLOGIN));
		}	
		session.removeAttribute(LoginSession.cLOGIN);
		session.removeAttribute(LoginSession.mLOGIN);
		session.invalidate();
	
		return "redirect:/";
	}
}