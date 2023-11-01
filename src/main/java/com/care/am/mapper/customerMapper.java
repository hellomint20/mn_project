package com.care.am.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.care.am.dto.customerDTO;

public interface customerMapper {
	public customerDTO getCustomer(String id);
	public customerDTO customerSearchId(@Param("inputName") String inputName,@Param("inputEmail") String inputEmail);
	public int customerPwChg(customerDTO dto);
	public void keepLogin(Map<String, Object> map);
	public int register(customerDTO dto);
	public customerDTO getCustomerSessionId( String cSessionId );
	public int customerPwdChg(customerDTO dto);
	public int customerModify(customerDTO dto);
	public int customerDelete(customerDTO dto);
}




























