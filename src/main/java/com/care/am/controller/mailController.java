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
		String title = "�ӽú�й�ȣ �߱� �����Դϴ�.";
		String pwd = content;
		
		String msg = "AniMedi���� �߼۵� �����Դϴ�.\n\n";
		msg += "������ �ӽ� ��й�ȣ�� " + pwd +" �Դϴ�\n";
		msg += "�α��� �Ͻð� ��й�ȣ ������ ���ּ���!\n\n";
		msg += "���õ� ���� �Ϸ� ��������~~~��";
		
		mails.tempPwdSendMail(toMail,title,msg);
						// �޴»�� ���� / ���� / ����
		
		return "redirect:/customerSearchPw";
	}
	
	
}
