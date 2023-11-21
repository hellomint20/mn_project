package com.care.am.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.care.am.common.LoginSession;
import com.care.am.service.payment.paymentService;
import com.care.am.service.reservation.reservationService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@Controller
public class paymentController {
	
	@Autowired paymentService as;
	@Autowired reservationService rs;
	
	private IamportClient api;
	
	public paymentController() {
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
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			result.put("result", Integer.toString(as.payResRegister(map)));
			result.put("userId", session.getAttribute(LoginSession.cLOGIN).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@ResponseBody
	@PostMapping("payResRefund") //결제 취소 및 예약 변경
	public String payResRefund(@RequestBody Map<String, String> map) { 
		String result = "";
		String num = map.get("num");
		String payment = map.get("payment");
		try {
			result = as.payCancle(num, payment);	//결제 취소
			if("0".equals(result)) {
				result = Integer.toString(rs.reserCancel(Integer.parseInt(num))); //예약 취소
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
