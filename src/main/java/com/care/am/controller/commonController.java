package com.care.am.controller;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
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
	
	@GetMapping("/") //���� Ȩ������
	public String main() {
		return "am/common/main";
	}

	@GetMapping("mediSearch") //���� ã�� ������
	public String mediSearch() {
		return "am/common/mediSearch";
	}
	
	@RequestMapping("logout") //�α׾ƿ�
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
		session.removeAttribute(LoginSession.sLOGIN);
		session.invalidate();
	
		return "redirect:/";
	}
	
	@RequestMapping("naverLogin2") 
	public String isComplete(HttpSession session) { 	
	return "/am/common/naverLogin2";  
	}
	
	@RequestMapping("naverCallBack") 
	public String navLogin(HttpServletRequest request) throws Exception {	 	
		return "/am/common/naverCallBack"; }	 
	
	
	@RequestMapping(value = "/personalInfo") 
	public void personalInfo(HttpServletRequest request) throws Exception {         
		String token = "YOUR_ACCESS_TOKEN";// ���̹� �α��� ���� ��ū; ���⿡ ������ ��ū���� �־��ݴϴ�.         
		String header = "Bearer " + token; // Bearer ������ ���� �߰�         
		try {             
			String apiURL = "https://openapi.naver.com/v1/nid/me";             
			URL url = new URL(apiURL);             
			HttpURLConnection con = (HttpURLConnection)url.openConnection();             
			con.setRequestMethod("GET");             
			con.setRequestProperty("Authorization", header);             
			int responseCode = con.getResponseCode();             
			BufferedReader br;             
			if(responseCode==200) { // ���� ȣ��                 
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));             
				} else {  // ���� �߻�                
					br = new BufferedReader(new InputStreamReader(con.getErrorStream()));             
					}             
			String inputLine;             
			StringBuffer response = new StringBuffer();             
			while ((inputLine = br.readLine()) != null) {                 
				response.append(inputLine);             
				}             br.close();             
				System.out.println(response.toString());         
				} catch (Exception e) {             
					System.out.println(e);         
					} 
		} 
	
}

	
