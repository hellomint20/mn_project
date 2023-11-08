package com.care.am.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.care.am.dto.customerDTO;
import com.care.am.service.loginLogic.loginLogicService;
import com.google.gson.Gson;

@RestController
public class kakaoContoller {

	Logger logger = LoggerFactory.getLogger(kakaoContoller.class);

	@Autowired
	loginLogicService lls;

	// 카카오 로그인
	@GetMapping("kakaoCallback")
	public String kakaoCallback(@RequestParam String code) {
		System.out.println(code);
		String access_token=lls.getKakaoAccessToken(code);
		System.out.println(access_token);
		try {
			customerDTO userInfo = lls.createKakaoUser(access_token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null; 
	}
	
}
