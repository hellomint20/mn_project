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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.care.am.service.reservation.reservationService;


@Controller
public class reservationController {

	@Autowired reservationService rs;
	
	
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
	public String reservationFormPage(@PathVariable String name, Model model, HttpSession session) {
		System.out.println("reservationFormPage "+name);
		model.addAttribute("name", name); //���õ� ����

		// �����ð� �������� 
		model.addAttribute("timeList", rs.mediTime(name));
		
		// �α����� ��� ���� ����Ʈ ��������
		String id = "1";
		model.addAttribute("p_list", rs.petList(id));
		
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
		map.put("cId", "1");
		int result = rs.reservationRegister(map);
		System.out.println(result);
		
		return Integer.toString(result);
	}
	
	//10/27 ���� ���׾�
	@GetMapping("reservationList") //�մ� ���� ����Ʈ
	public String reservationList(@RequestParam String id, Model model) {
		model.addAttribute("list",rs.reservationList(id));
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

	@ResponseBody
	@PostMapping("reservationCount") //�ð��� ������ �� Ȯ��
	public Map<String, String> reservationCount(@RequestBody Map<String, Object> map) {
		return rs.reservationCount(map);
	}

	
}
