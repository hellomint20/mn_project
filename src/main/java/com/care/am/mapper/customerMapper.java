package com.care.am.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.care.am.dto.customerDTO;
import com.care.am.dto.recentlyViewDTO;

public interface customerMapper {
	public customerDTO getCustomer(String id);
	public boolean idCheck(String id);
	public customerDTO customerSearchId(@Param("inputName") String inputName,@Param("inputEmail") String inputEmail);
	public int customerPwChg(customerDTO dto);
	public void keepLogin(Map<String, Object> map);
	public int register(customerDTO dto);
	public customerDTO getCustomerSessionId( String cSessionId );
	public int customerPwdChg(customerDTO dto);
	public int customerModify(customerDTO dto);
	public int customerDelete(customerDTO dto);
	public int addRecentlyView(recentlyViewDTO rDto);
	public void delRecentlyView(String cId);
	public List<Map<String, String>> getRecentlyView(String cId);
}




























