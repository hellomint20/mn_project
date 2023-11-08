package com.care.am.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.am.dto.pageDTO;
import com.care.am.service.reservation.reservationService;

@Controller
public class reservationController {

	@Autowired reservationService rs;
	
	
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
	
	@PostMapping("reservationRegister") 
	public void reservation(String id) {
		
	}
	
	@GetMapping("reservationList") 
	public String reservationList(@RequestParam String id, Model model) {
		model.addAttribute("list",rs.reservationList(id));
		return "am/reservation/reservationList";
	}
	
	//병원 예약상태 관련(병원 기준 - 새로운 접수)
	@GetMapping("reservationStateWait") //병원 예약상태
	public String reservationState(@RequestParam String id, Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		
		model.addAttribute("wait", rs.waitList(id, page)); //새로운접수
		model.addAttribute("waitPaging", rs.waitListPaging(page, id));
		
		return "am/reservation/reservationStateWait";
	}
	
	//병원 예약상태 관련(병원기준 - 승인취소)
	@GetMapping("reservationStateAC")
	public String reservationStateAC(@RequestParam String id, Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		model.addAttribute("ac", rs.ACList(id, page)); //승인취소
		model.addAttribute("ACPaging", rs.ACListPaging(page, id));
		
		return "am/reservation/reservationStateAC";
	}
	
	@GetMapping("reservationCancel") 
	public void reservationCancel(@RequestParam String id, @RequestParam int num,
								HttpServletResponse res) throws Exception {
		String msg = rs.reserCancel(id, num);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(msg);
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

	
}