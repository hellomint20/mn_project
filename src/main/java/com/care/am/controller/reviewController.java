package com.care.am.controller;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.am.dto.reviewDTO;
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
		return "am/review/fixedForm";
	}
	
	@PostMapping("fixedForm")
	public void fixedForm (reviewDTO dto, @RequestParam int num,HttpServletResponse res) throws Exception{		
		String msg = bs.fixedForm(dto, num);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(msg);
	}
	
	@GetMapping("myReview")
	public String myReview() {
		return "am/review/myReview";
	}
	
	
	
}
