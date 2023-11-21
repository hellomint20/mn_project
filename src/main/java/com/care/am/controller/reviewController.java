package com.care.am.controller;

import java.util.Map;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.PostMapping;

import com.care.am.dto.reviewDTO;
import com.care.am.service.reservation.reservationService;
import com.care.am.service.review.reviewService;

@Controller
public class reviewController {
	
	@Autowired reservationService rs;
	@Autowired reviewService bs;
	
	@GetMapping("fixedForm") //���� ��� â
	public String fixedForm(@RequestParam int num,@RequestParam String id, Model model) {
		Map<String, String> info =  rs.getResInfo(num);
		model.addAttribute("info", info);
		return "am/review/fixedForm";
	}
	
	@PostMapping("fixedForm") //���� ���
	public void fixedForm (reviewDTO dto, @RequestParam int num, HttpServletResponse res) throws Exception{		
		String id = dto.getcId();
		
		String msg = bs.fixedForm(dto,id, num);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(msg);
	}
	
	@GetMapping("boardList") //��� ���� ����Ʈ
	public String boardList(Model model) {
		model.addAttribute("list", bs.boardList());
		return "am/review/boardList";
	}
	
	@GetMapping("reviewList") //���� �� �ı� ����Ʈ
	public String reviewList(Model model, @RequestParam String id) {
		model.addAttribute("list", bs.reviewList(id));
		return "am/review/reviewList";
	}
	
	@GetMapping("myReview") //���� �� �ı� �󼼺���
	public String myReview(@RequestParam int num, Model model) {
		model.addAttribute("detail", bs.myReview(num));
		return "am/review/myReview";
	}
	
	@GetMapping("modiForm") //�ı� ���� ������
	public String modiForm(@RequestParam int num, Model model) {
		model.addAttribute("list", bs.myReview(num));
		return "am/review/modiForm";
	}
	
	@PostMapping("modiForm")
	public void modiForm(reviewDTO dto, HttpServletResponse res) throws Exception {
		String msg = bs.modiForm(dto);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(msg);
	}
	
	@GetMapping("delete")
	public void delete(@RequestParam int num,@RequestParam String id, HttpServletResponse res)throws Exception {
		System.out.println(num);
		
		String msg = bs.delete(id, num);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(msg);
	}
	
}