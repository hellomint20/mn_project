package com.care.am.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.am.dto.pageDTO;
import com.care.am.service.reservation.reservationService;

@Controller
public class reservationController {
	
	@Autowired reservationService rs;
	
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
	public String reservationList() {
		return "am/reservation/reservationList";
	}
	
	//���� ������� ����(���� ����)
	@GetMapping("reservationState") //���� �������
	public String reservationState(@RequestParam String id, Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		
		
		model.addAttribute("wait", rs.waitList(id, page)); //���ο�����
		model.addAttribute("waitPaging", rs.waitListPaging(page, id));
		
		model.addAttribute("ac", rs.ACList(id, page)); //�������
		model.addAttribute("ACPaging", rs.ACListPaging(page, id));
		
		System.out.println("11:"+ rs.waitList(id, page));
		System.out.println("22:"+ rs.waitListPaging(page, id));
		
		//model.addAttribute("list", rs.mediReservationList(id, page)); //�������
		//model.addAttribute("waitList", rs.mediReservationWaitList(id)); //���
		
		List<Map<String, Object>> pagingList = rs.paging(page, id);
		//System.out.println("page: "+page);
		System.out.println("pagingList: "+pagingList);
		
		//pageDTO pageDTO = rs.pagingParam(page, id);
		
		//model.addAttribute("page", pagingList);
		//model.addAttribute("paging", pageDTO);
		
		//Map<String, Object> map = rs.paging(num);
		//model.addAttribute("page", map.get("paging"));
		
		return "am/reservation/reservationState";
	}
	
	@PostMapping("reservationState") //���� ���� ����, ��� (map���� ��� �ޱ�)
	public void reservationState() {
		
	}

	@GetMapping("reservationApplyPopup") 
	public String reservationApplyPopup(@RequestParam int rNum, Model model) {
		
		model.addAttribute("info", rs.reservationInfo(rNum));
		
		return "am/reservation/reservationApplyPopup";
	}
	
}
