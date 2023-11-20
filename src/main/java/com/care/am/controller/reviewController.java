package com.care.am.controller;

import java.util.Map;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.PostMapping;

import com.care.am.dto.reviewDTO;
import com.care.am.page.reviewPagination;
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
	
	@GetMapping("boardList")
	public String boardList(Model model, reviewPagination rp,
							@RequestParam(value="nowPage", required = false)String nowPage,
							@RequestParam(value="cntPerPage", required = false)String cntPerPage) {

		int total = bs.reviewAll();
		
		System.out.println(total);
		System.out.println(nowPage);
		System.out.println(cntPerPage);
		
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) { 
			cntPerPage = "5";
		}
		
		rp = new reviewPagination(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		model.addAttribute("paging", rp);
		model.addAttribute("list", bs.reviewSel(rp));
		System.out.println("ctrl: " +bs.reviewSel(rp));
		
		return "am/review/boardList";
	}
	
	@GetMapping("reviewList")
	public String reviewList(Model model, @RequestParam String id,reviewPagination rp,
							@RequestParam(value="nowPage", required = false)String nowPage,
							@RequestParam(value="cntPerPage", required = false)String cntPerPage) {
		
		int total = bs.myReviewAll(id);
		
		System.out.println(total);
		System.out.println(nowPage);
		System.out.println(cntPerPage);
		
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) { 
			cntPerPage = "5";
		}
		
		rp = new reviewPagination(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		model.addAttribute("paging", rp);
		model.addAttribute("list", bs.myReviewList(rp, id));
		System.out.println("ctrl: " +bs.myReviewList(rp, id));
		
		return "am/review/reviewList";
	}
	
	
	@PostMapping("fixedForm")
	public void fixedForm (reviewDTO dto, @RequestParam int num,HttpServletResponse res) throws Exception{		
		String msg = bs.fixedForm(dto, num);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(msg);
	}
	
	@GetMapping("myReview")
	public String myReview(@RequestParam int num, Model model) {
		model.addAttribute("detail", bs.myReview(num));
		return "am/review/myReview";
	}
	
	@GetMapping("modiForm")
	public String modiForm(@RequestParam int num, Model model) {
		model.addAttribute("list", bs.myReview(num));
		System.out.println(bs.myReview(num).getcId());
		System.out.println(bs.myReview(num).getmName());
		System.out.println(bs.myReview(num).getRvCont());
		System.out.println(bs.myReview(num).getrDate());
		System.out.println(bs.myReview(num).getRvNo());
		System.out.println(bs.myReview(num).getRvTitle());
		return "am/review/modiForm";
	}
	
	
}
