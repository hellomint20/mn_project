package com.care.am.mapper;

import java.util.Map;

import com.care.am.dto.customerDTO;

public interface customerMapper {
	public customerDTO getCustomer(String id);
	public void keepLogin(Map<String, Object> map);
	public int register(customerDTO dto);
	public customerDTO getCustomerSessionId( String cSessionId );
	public int customerModify(customerDTO dto);
}




























