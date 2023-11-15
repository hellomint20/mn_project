package com.care.am.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.am.common.LoginSession;
import com.care.am.service.admin.adminService;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.care.am.common.LoginSession;
import com.care.am.service.admin.adminService;
import com.care.am.service.reservation.reservationService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@Controller
public class adminController {
	
	@Autowired adminService as;
	@Autowired reservationService rs;

	private IamportClient api;
	
	
	
	public adminController() {
		this.api = new IamportClient("1221163661378568","FsZsjC291mrfnLngPLepGgN3R5jxJukbmMmg9oG8dTuyvkjKQpiFEaiPED97F6r6nwNWttDYwBYUjs24");
	}
	
	@ResponseBody
	@RequestMapping(value="/verifyIamport/{imp_uid}")
	public IamportResponse<Payment> paymentByImpUid(Model model, Locale locale
													, HttpSession session, @PathVariable(value= "imp_uid") String imp_uid) throws IamportResponseException, IOException {	
		System.out.println(imp_uid);
		System.out.println(api.paymentByImpUid(imp_uid));
		return api.paymentByImpUid(imp_uid);
	}
	
	@ResponseBody
	@PostMapping("payResRegister") //결제 정보 및 병원 예약 DB 등록
	public Map<String, Object> payResRegister(@RequestBody Map<String, Object> map, HttpSession session) {
		map.put("cId", session.getAttribute(LoginSession.cLOGIN).toString());
		System.out.println("되어라 "+map.get("impUid"));
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			result.put("result", Integer.toString(as.payResRegister(map)));
			result.put("userId", session.getAttribute(LoginSession.cLOGIN).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@GetMapping("payResRefund") //결제 취소
	public String payResRefund(@RequestParam String rNum) { 
		System.out.println("왔다");
		as.orderCancle(rNum);
		return "redirect:/";
	}
}
