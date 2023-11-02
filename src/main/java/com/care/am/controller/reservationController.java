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
	public String reservationState(@RequestParam String id, Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		
		
		model.addAttribute("wait", rs.waitList(id, page)); //새로운접수
		model.addAttribute("waitPaging", rs.waitListPaging(page, id));
		
		model.addAttribute("ac", rs.ACList(id, page)); //승인취소
		model.addAttribute("ACPaging", rs.ACListPaging(page, id));
		
		System.out.println("11:"+ rs.waitList(id, page));
		System.out.println("22:"+ rs.waitListPaging(page, id));
		
		//model.addAttribute("list", rs.mediReservationList(id, page)); //승인취소
		//model.addAttribute("waitList", rs.mediReservationWaitList(id)); //대기
		
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
	
	@PostMapping("reservationState") //병원 예약 승인, 취소 (map으로 묶어서 받기)
	public void reservationState() {
		
	}

	@GetMapping("reservationApplyPopup") 
	public String reservationApplyPopup(@RequestParam int rNum, Model model) {
		
		model.addAttribute("info", rs.reservationInfo(rNum));
		
		return "am/reservation/reservationApplyPopup";
	}
	
}
