package com.care.am.service.payment;

import java.util.Map;

public interface paymentService {
	public int payResRegister(Map<String, Object> map); //���� ���� 
	public String payCancle(String rNum, String pay); //���� ���
}
