package com.care.am.service.payment;

import java.util.Map;

public interface paymentService {
	public int payResRegister(Map<String, Object> map); //병원 예약 
	public String payCancle(String rNum, String pay); //결제 취소
}
