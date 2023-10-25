package com.care.am.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.care.am.dto.mediDTO;
import com.care.am.service.reservation.reservationService;

@Controller
public class reservationController {
	@Autowired
	reservationService rs;
	
	//병원 예약 관련(손님 기준)
	@GetMapping("reservation") //병원 예약 기본 페이지
	public String reservation(Model model) {
		model.addAttribute("list", rs.mediList()); //medi List 가져오기
		return "am/reservation/reservationPage";
	}
	
	@ResponseBody
	@PostMapping("reservation/mediInfo") //병원 상세정보 팝업
	public Map<String, Object> mediInfo(@RequestBody String mediName){
		System.out.println("con"+mediName);
		System.out.println("con"+rs.mediInfo(mediName));
		return rs.mediInfo(mediName);
	}

	@RequestMapping(value = "reservationForm/page/{name}") //병원 예약 페이지
	public String reservationFormPage(@PathVariable String name, Model model) {
		System.out.println("reservationFormPage "+name);
		model.addAttribute("name", name); //선택된 병원
		// 로그인한 사람 동물 리스트 가져오기
		// 영업시간 가져오기 
		model.addAttribute("p_list", rs.petList());
		return "am/reservation/reservationForm";
	}
	
	@GetMapping("reservationPopup")//예약완료후팝업창
	public String reservationPopup() {
		return "am/reservation/reservationPopup";
	}
	
	@PostMapping("reservationRegister") //병원 예약 DB 등록
	public void reservation(String id) {
		
	}
	
	@GetMapping("reservationList") //손님 예약 리스트
	public String reservationList() {
		return "am/reservation/reservationList";
	}
	
	//병원 예약상태 관련(병원 기준)
	@GetMapping("reservationState") //병원 예약상태
	public String reservationState() {
		return "am/reservation/reservationState";
	}
	
	@PostMapping("reservationState") //병원 예약 승인, 취소 (map으로 묶어서 받기)
	public void reservationState(String id) {
		
	}

	@GetMapping("reservationApplyPopup") 
	public String reservationApplyPopup(String id) {
		return "am/reservation/reservationApplyPopup";
	}
	
}
