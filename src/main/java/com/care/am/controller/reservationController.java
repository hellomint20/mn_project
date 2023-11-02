package com.care.am.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

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


	@GetMapping("reservation")
	public String reservation() {
		return "am/reservation/reservationPage";
	}
	
	@GetMapping("reservationForm")
	public String reservationForm() {
		return "am/reservation/reservationForm";
	}
	
	@GetMapping("reservationPopup")
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
	
	@GetMapping("reservationList") 
	public String reservationList(@RequestParam String id, Model model) {
		model.addAttribute("list",rs.reservationList(id));
		return "am/reservation/reservationList";
	}
	
	@GetMapping("reservationCancel") 
	public void reservationCancel(@RequestParam String id, @RequestParam int num,
								HttpServletResponse res) throws Exception {
		String msg = rs.reserCancel(id, num);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(msg);
	}
	
	
	
	

	@GetMapping("reservationState")
	public String reservationState(@RequestParam String id, Model model/*, @RequestParam(required = false, defaultValue = "1") int num*/) {
		model.addAttribute("list", rs.mediReservationList(id));
		model.addAttribute("waitList", rs.mediReservationWaitList(id));
		
		//Map<String, Object> map = rs.paging(num);
		//model.addAttribute("page", map.get("paging"));
		
		return "am/reservation/reservationState";
	}
	
	@GetMapping("reserState1") 
	public String reserState1(@RequestParam int num, @RequestParam String email, @RequestParam String mId) {
		int result = rs.reserState(num, 1);
		if(result == 1) {
			String toMail = email;
			return "redirect:/reserState1/"+toMail+"/"+mId+"/";
		}
		return "redirect:/reservationState";
	}
	
	@PostMapping("reserState2")
    public String reserState2(@RequestParam String email,@RequestParam int num,
        @RequestParam String mId, @RequestParam String cont) {
		int result = rs.reserState(num, 0);
		
		if(result == 1) {
			String toMail = email;
			return "redirect:/reserState2/"+toMail+"/"+cont+"/"+mId+"/";
		}
		return "redirect:/reservationState";
	}

	@GetMapping("reservationApplyPopup") 
	public String reservationApplyPopup(@RequestParam int rNum, Model model) {
		System.out.println(rNum);
		model.addAttribute("info", rs.reservationInfo(rNum));
		model.addAttribute("num", rNum);
		return "am/reservation/reservationApplyPopup";
	}
	@ResponseBody
	@PostMapping("reservationCount") //�ð��� ������ �� Ȯ��
	public Map<String, String> reservationCount(@RequestBody Map<String, Object> map) {
		return rs.reservationCount(map);
	}
	
}
