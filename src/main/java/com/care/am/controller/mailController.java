package com.care.am.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.care.am.service.mail.mailService;

@Controller
public class mailController {
	@Autowired mailService mails;
	
	
	@RequestMapping(value="/customerSearchPw/{toMail}/{content}/", method=RequestMethod.GET)
	String tempPwdSendMail(@PathVariable String toMail, @PathVariable String content, HttpServletResponse res)throws Exception{
		String title = "임시비밀번호 발급 메일입니다.";
		String pwd = content;
		
		String msg = "AniMedi에서 발송된 메일입니다.\n\n";
		msg += "고객님의 임시 비밀번호는 " + pwd +" 입니다\n";
		msg += "로그인 하시고 비밀번호 변경을 해주세요!\n\n";
		msg += "오늘도 좋은 하루 보내세요~~~♥";
		
		mails.tempPwdSendMail(toMail,title,msg);
						// 받는사람 메일 / 제목 / 내용
		
		return "redirect:/customerSearchPw";
	}
	
	
}
