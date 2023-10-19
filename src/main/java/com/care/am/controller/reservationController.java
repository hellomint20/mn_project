package com.care.am.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class reservationController {
	
	//병원 예약 관련(손님 기준)
	@GetMapping("reservation") //병원 예약 기본 페이지
	public String reservation() {
		return "am/reservation/reservationPage";
	}
	
	@GetMapping("reservationForm") //병원 예약 하기 페이지
	public String reservationForm() {
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

}
