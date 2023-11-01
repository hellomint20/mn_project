package com.care.am.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.am.common.GetMessage;
import com.care.am.service.customer.customerService;
import com.care.am.service.reservation.reservationService;

import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class reservationController {

	@Autowired reservationService rs;
	@Autowired customerService cs;
	
	//���� ���� ����(�մ� ����)
	@GetMapping("reservation") //���� ���� �⺻ ������
	public String reservation() {
		return "am/reservation/reservationPage";
	}
	
	@GetMapping("reservationForm") //���� ���� �ϱ� ������
	public String reservationForm() {
		return "am/reservation/reservationForm";
	}
	
	@GetMapping("reservationPopup")//����Ϸ����˾�â
	public String reservationPopup() {
		return "am/reservation/reservationPopup";
	}
	
	@PostMapping("reservationRegister") //���� ���� DB ���
	public void reservation(String id) {
		
	}
	
	@GetMapping("reservationList") //�մ� ���� ����Ʈ
	public String reservationList(@RequestParam String id, Model model) {
		model.addAttribute("list",rs.reservationList(id));
		return "am/reservation/reservationList";
	}
	
	@GetMapping("reservationCancel") // �մ� -> ���� ���
	public void reservationCancel(@RequestParam String id, @RequestParam int num,
								HttpServletResponse res) throws Exception {
		String msg = rs.reserCancel(id, num);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(msg);
	}
	
	
	
	
	//���� ������� ����(���� ����)
	@GetMapping("reservationState") //���� �������
	public String reservationState(@RequestParam String id, Model model/*, @RequestParam(required = false, defaultValue = "1") int num*/) {
		model.addAttribute("list", rs.mediReservationList(id));
		model.addAttribute("waitList", rs.mediReservationWaitList(id));
		
		//Map<String, Object> map = rs.paging(num);
		//model.addAttribute("page", map.get("paging"));
		
		return "am/reservation/reservationState";
	}
	
	@GetMapping("reserState") //���� ���� ����, ��� (map���� ��� �ޱ�)
	public String reserState(@RequestParam int num) {
		System.out.println("ctrl"+num);
		int result = rs.reserState(num);
		System.out.println("ctrl"+result);
		if(result == 1) {
			return "redirect:/reserState/"+"wjdtjs3558@naver.com"+"/";
		}
		return "redirect:/reservationState";
	}

	@GetMapping("reservationApplyPopup") 
	public String reservationApplyPopup(@RequestParam int rNum, Model model) {
		model.addAttribute("info", rs.reservationInfo(rNum));
		return "am/reservation/reservationApplyPopup";
	}

	
}
