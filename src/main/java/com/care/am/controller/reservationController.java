package com.care.am.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.care.am.service.reservation.reservationService;

@Controller
public class reservationController {
	@Autowired
	reservationService rs;
	
	//���� ���� ����(�մ� ����)
	@GetMapping("reservation") //���� ���� �⺻ ������
	public String reservation(Model model) {
		model.addAttribute("list", rs.mediList()); //medi List ��������
		return "am/reservation/reservationPage";
	}
	
	@ResponseBody
	@PostMapping("reservation/mediInfo") //���� ������ �˾�
	public Map<String, Object> mediInfo(@RequestBody String mediName){
		System.out.println("pop : "+rs.mediInfo(mediName));
		return rs.mediInfo(mediName);
	}

	@RequestMapping(value = "reservationForm/page/{name}") //���� ���� ������
	public String reservationFormPage(@PathVariable String name, Model model) {
		System.out.println("reservationFormPage "+name);
		model.addAttribute("name", name); //���õ� ����

		// �����ð� �������� 
		System.out.println(rs.mediTime(name));
		model.addAttribute("timeList", rs.mediTime(name));
		
		// �α����� ��� ���� ����Ʈ ��������
		model.addAttribute("p_list", rs.petList());
		
		return "am/reservation/reservationForm";
	}

	@GetMapping("reservationPopup")//����Ϸ����˾�â
	public String reservationPopup() {
		return "am/reservation/reservationPopup";
	}
	
	@ResponseBody
	@PostMapping("reservationRegister") //���� ���� DB ���
	public String reservationRegister(@RequestBody Map<String, Object> map, HttpSession session) {
		System.out.println(map);
		map.put("cId", "yh");
		int result = rs.reservationRegister(map);
		System.out.println(result);
		
		return Integer.toString(result);
	}
	
	@GetMapping("reservationList") //�մ� ���� ����Ʈ
	public String reservationList() {
		return "am/reservation/reservationList";
	}
	
	//���� ������� ����(���� ����)
	@GetMapping("reservationState") //���� �������
	public String reservationState() {
		return "am/reservation/reservationState";
	}
	
	@PostMapping("reservationState") //���� ���� ����, ��� (map���� ��� �ޱ�)
	public void reservationState(String id) {
		
	}

	@GetMapping("reservationApplyPopup") 
	public String reservationApplyPopup(String id) {
		return "am/reservation/reservationApplyPopup";
	}
	
}
