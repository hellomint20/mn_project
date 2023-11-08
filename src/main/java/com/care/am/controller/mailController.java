package com.care.am.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.care.am.common.LoginSession;
import com.care.am.service.mail.mailService;

@Controller
public class mailController implements LoginSession{
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
		
		return "redirect:/customerLogin";
	}
	@RequestMapping(value="/reserState1/{toMail}/{mId}/", method=RequestMethod.GET)
	String sendMail(@PathVariable String toMail, @PathVariable String mId, 
						HttpServletResponse res)throws Exception{
		System.out.println("state1");
		String title = "AniMedi ������Ȳ�Դϴ�.";
		String msg = "AniMedi���� �߼۵� �����Դϴ�.\n\n";
		msg += "������ ������ Ȯ���Ǿ� �����帳�ϴ�\n";
		msg += "���� Ȯ���Ͻð� �� �ð��� �°� �������ּ���!\n\n";
		msg += "���õ� ���� �Ϸ� ��������~~~��";
		
		mails.tempPwdSendMail(toMail,title,msg);
		// �޴»�� ���� / ���� / ����
		
		return "redirect:/reservationState?id="+ mId;
	}
	
	@RequestMapping(value="/reserState2/{toMail}/{cont}/{mId}/", method=RequestMethod.GET)
	String sendMail(@PathVariable String toMail, @PathVariable String cont,@PathVariable String mId, 
						HttpServletResponse res)throws Exception{
		
		String title = "AniMedi ������Ȳ�Դϴ�.";
		String msg = "AniMedi���� �߼۵� �����Դϴ�.\n\n";
		msg += "������ ������ ��ҵǾ� �����帳�ϴ�\n";
		msg += "���� ��� ����: "+ cont+ "\n\n";
		msg += "�˼��մϴ� ������ �̿����ֽñ� �ٶ��ϴ�.\n\n";
		msg += "���õ� ���� �Ϸ� ��������";
		
		mails.tempPwdSendMail(toMail,title,msg);
		// �޴»�� ���� / ���� / ����
		
		return "redirect:/reservationState?id="+ mId;
	}
}