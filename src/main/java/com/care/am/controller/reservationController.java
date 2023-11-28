package com.care.am.controller;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.care.am.common.LoginSession;
import com.care.am.dto.customerDTO;
import com.care.am.page.customerPagination;
import com.care.am.page.reservationPagination;
import com.care.am.service.customer.customerService;
import com.care.am.service.payment.paymentService;
import com.care.am.service.reservation.reservationService;

@Controller
public class reservationController {

	@Autowired 
	private reservationService rs;
	
	@Autowired
	private paymentService as;
	
	@Autowired
	private customerService cs;

	@GetMapping("reservation")
	public String reservation(Model model, reservationPagination pag
			, @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage)  {
		
		int mediCnt = rs.mediList().size();
     
    	if (nowPage == null && cntPerPage == null) { 
    		nowPage = "1";
    		cntPerPage = "10";
    	} else if (nowPage == null) {
    		nowPage = "1";
    	} else if (cntPerPage == null) { 
    		cntPerPage = "10";
    	}
    	
    	pag = new reservationPagination(mediCnt, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
    	model.addAttribute("paging", pag);
    	model.addAttribute("viewAll", rs.mediSelectList(pag));
    	model.addAttribute("search", 0);
    	
		return "am/reservation/reservationPage";
	}

	@GetMapping("reservationSearch")
	public String reservationSearch(@RequestParam String mName, Model model, reservationPagination pag,
									@RequestParam(value = "nowPage", required = false)String nowPage,
									@RequestParam(value = "cntPerPage", required = false)String cntPerPage) {
		int mediCnt = rs.mediSearch(mName);
		
		if(nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "10";
		}else if(nowPage == null) {
			nowPage = "1";
		}else if(cntPerPage == null) {
			cntPerPage = "10";
		}
		pag = new reservationPagination(mediCnt, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
    	model.addAttribute("paging", pag);
    	model.addAttribute("viewAll", rs.mediSelectSearch(mName, pag));
    	model.addAttribute("search", 1);
    	model.addAttribute("mName", mName);
		
		return "am/reservation/reservationPage";
	}
	
	@ResponseBody
	@PostMapping("reservation/mediInfo")
	public Map<String, Object> mediInfo(@RequestBody String mediId){
		return rs.mediInfo(mediId);
	}
	
	@PostMapping("reservationForm/page")
	public String reservationFormPage(@RequestParam String mediId, Model model, HttpSession session ) {
		
		model.addAttribute("mediInfo", rs.mediInfo(mediId));

		model.addAttribute("timeList", rs.mediTime(mediId));
		
		model.addAttribute("p_list", rs.petList(session.getAttribute(LoginSession.cLOGIN).toString()));
		
		return "am/reservation/reservationForm";
	}

	@GetMapping("reservationPopup")
	public String reservationPopup(Model model, HttpSession session) {
	
		customerDTO dto = cs.getCustomerInfo(session.getAttribute(LoginSession.cLOGIN).toString());
		model.addAttribute("customer", dto);
		
		return "am/reservation/reservationPopup";
	}
	
	@GetMapping("reservationList")
	public String reservationList(@RequestParam String id, Model model, customerPagination pag
			, @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
				
		int customerCnt = rs.reservationList(id).size(); 
			     
    	if (nowPage == null && cntPerPage == null) { 
    		nowPage = "1";
    		cntPerPage = "4"; 
    	} else if (nowPage == null) {
    		nowPage = "1";
    	} else if (cntPerPage == null) { 
    		cntPerPage = "4";
    	}
    	
    	pag = new customerPagination(customerCnt, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
    	
    	model.addAttribute("paging", pag);
    	model.addAttribute("viewAll", rs.customerResList(id, pag));
    	System.out.println(rs.customerResList(id, pag));
		
		return "am/reservation/reservationList";
	}
	
	@GetMapping("reservationStateWait")
	public String reservationState(@RequestParam String id, Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		
		model.addAttribute("wait", rs.waitList(id, page));
		model.addAttribute("waitPaging", rs.waitListPaging(page, id));
		
		return "am/reservation/reservationStateWait";
	}
	
	@GetMapping("reservationStateA")
	public String reservationStateA(@RequestParam String id, Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		model.addAttribute("a", rs.AList(id, page));
		model.addAttribute("APaging", rs.AListPaging(page, id));
		return "am/reservation/reservationStateA";
	}

	@PostMapping("reservationStateA")
	   public String reservationStateA(@RequestParam int r_fix, @RequestParam String id, @RequestParam int r_num, @RequestParam int page) {
	      rs.fix(id, r_fix, r_num);
	      return "redirect:/reservationStateA?id="+id+"&page="+page;
	}
	
	@GetMapping("reservationStateC")
	public String reservationStateC(@RequestParam String id, Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		model.addAttribute("c", rs.CList(id, page));
		model.addAttribute("CPaging", rs.CListPaging(page, id));
		
		return "am/reservation/reservationStateC";
	}
	
	@PostMapping("reservationStateC")
	public String reservationStateC(@RequestParam int r_fix, @RequestParam String id, @RequestParam int r_num, @RequestParam int page) {
		rs.resReturn(r_fix, r_num);
		return "redirect:/reservationStateC?id="+id+"&page="+page;
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
	public String reserState2(@RequestParam String email, @RequestParam int num, @RequestParam String mId,
			@RequestParam String cont) {
		String cancle = "";
		String payment = "5000";
		cancle = as.payCancle(Integer.toString(num), payment);
		if ("0".equals(cancle)) {
			int result = 0;
			result = rs.reserState(num, 0);
			if (result == 1) {
				String toMail = email;
				return "redirect:/reserState2/" + toMail + "/" + cont + "/" + mId + "/"+ payment + "/";
			}
		}
		return "redirect:/reservationState";
	}

	@GetMapping("reservationApplyPopup") 
	public String reservationApplyPopup(@RequestParam int rNum, Model model) {
		model.addAttribute("info", rs.reservationInfo(rNum));
		model.addAttribute("num", rNum);
		return "am/reservation/reservationApplyPopup";
	}
	
	@ResponseBody
	@PostMapping("reservationCount")
	public Map<String, String> reservationCount(@RequestBody Map<String, Object> map) {
		return rs.reservationCount(map);
	}
	
	@ResponseBody
	@PostMapping("reservationCheck")
	public String reservationCheck(HttpSession session, @RequestBody Map<String, String> map ) {
		map.put("cId", session.getAttribute(LoginSession.cLOGIN).toString());
		
		String size = "";
		if(rs.reservationCheck(map) != null) {
			size = "1";
		} 
		return size;
	}
}
