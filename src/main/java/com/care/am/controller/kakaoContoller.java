package com.care.am.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.care.am.common.LoginSession;
import com.care.am.dto.customerDTO;
import com.care.am.service.loginLogic.loginLogicService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RestController
public class kakaoContoller {

	Logger logger = LoggerFactory.getLogger(kakaoContoller.class);

	@Autowired
	loginLogicService lls;

	// 카카오 로그인
	@GetMapping("kakaoCallback")
	public void kakaoCallback(@RequestParam String code, HttpSession session, HttpServletResponse response) {
		System.out.println(code);
		String access_token=lls.getKakaoAccessToken(code);
		System.out.println(access_token);
		try {
			JsonParser parser = new JsonParser();
			JsonObject json = parser.parse(access_token).getAsJsonObject();

			// "access_token" 필드의 값을 가져오기
			String accessToken = json.get("access_token").getAsString();
			System.out.println("ctrl: " +accessToken);
			customerDTO userInfo = lls.createKakaoUser(accessToken);
			session.setAttribute(LoginSession.cLOGIN, userInfo.getcId());
			String redirect_uri = "/am";
			response.sendRedirect(redirect_uri);
			} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
