package com.care.am.service.admin;

import java.util.Map;

public interface adminService {
	public int payResRegister(Map<String, Object> map); //병원 예약 
	public void orderCancle(String rNum); //결제 취소
}
