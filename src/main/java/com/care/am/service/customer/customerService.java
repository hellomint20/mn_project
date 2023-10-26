package com.care.am.service.customer;

import com.care.am.dto.customerDTO;

public interface customerService {
	public String register(customerDTO dto);
	public int logChk(String id, String pw);
	public void keepLogin(String cSessionId, String cId);
	public customerDTO getCustomerSessionId(String cSessionId);
	public customerDTO getCustomerInfo(String cId);
	public String customerPwdChg(customerDTO dto,String pw, String newPw);
	public String customerModify(customerDTO dto);
	public String customerPwdChk(String id, String Pw);
	public String customerDelete(customerDTO dto, String pw);
}
