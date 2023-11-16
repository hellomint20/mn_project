package com.care.am.service.customer;


import java.util.List;
import java.util.Map;

import com.care.am.dto.customerDTO;

public interface customerService {
	public String register(customerDTO dto);
	public boolean idCheck(String id);
	public String customerSearchId(String inputName, String inputEmail);
	public customerDTO naverLogin(String apiResult) throws Exception;
	public customerDTO googleLogin(String profile);
	public customerDTO customerSearchPw(String inputId, String inputName, String inputTel);
	public String makeRandomPw();
	public int customerPwChg(String tempPwd, customerDTO dto);
	public int logChk(String id, String pw);
	public void keepLogin(String cSessionId, String cId);
	public customerDTO getCustomerSessionId(String cSessionId);
	public customerDTO getCustomerInfo(String cId);
	public String customerPwdChg(customerDTO dto,String pw, String newPw);
	public String customerModify(customerDTO dto);
	public String customerPwdChk(String id, String Pw);
	public String customerDelete(customerDTO dto, String pw);
	
	public void addrecentlyView(String mediCookie);
	public void delRecentlyView(String cId);
	public List<Map<String, String>> getRecentlyView(List<String> recentlyViewed,String cId);
}
