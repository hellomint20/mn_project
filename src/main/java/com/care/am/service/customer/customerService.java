package com.care.am.service.customer;

import com.care.am.dto.customerDTO;

public interface customerService {
	public String register(customerDTO dto);
	public int logChk(String id, String pw);
	public void keepLogin(String cSessionId, String cId);
	public customerDTO getCustomerSessionId(String cSessionId);
	public customerDTO getCustomerInfo(String cId);
	public String customerModify(customerDTO dto);
}
