package com.care.am.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.am.service.reservation.reservationService;
import com.care.am.service.review.reviewService;

@Controller
public class reviewController {
	
	@Autowired reservationService rs;
	@Autowired reviewService bs;
	
	@GetMapping("fixedForm")
	public String fixedForm(@RequestParam int num, Model model) {
		Map<String, String> info =  rs.getResInfo(num);
		model.addAttribute("info", info);
		return "am/board/fixedForm";
	}
	
	@GetMapping("boardList")
	public String boardList(Model model) {
		model.addAttribute("list", bs.boardList());
		return "am/board/boardList";
	}
	
	@GetMapping("reviewList")
	public String List(Model model, @RequestParam String id) {
		model.addAttribute("list", bs.reviewList(id));
		return "am/board/reviewList";
	}
	
}
