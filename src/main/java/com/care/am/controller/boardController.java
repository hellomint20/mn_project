package com.care.am.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.am.service.reservation.reservationService;

@Controller
public class boardController {
	
	@Autowired reservationService rs;
	
	@GetMapping("fixedForm")
	public String fixedForm(@RequestParam int num, Model model) {
		Map<String, String> info =  rs.getResInfo(num);
		model.addAttribute("info", info);
		return "am/board/fixedForm";
	}
	
	
}
