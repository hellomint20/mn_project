package com.care.am.service.customer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.am.common.GetMessage;
import com.care.am.dto.customerDTO;
import com.care.am.mapper.customerMapper;

@Service
public class customerServiceImpl implements customerService{
	
	@Autowired customerMapper cm;
	
	public String register(customerDTO dto) {
		int result = cm.register(dto);
		if(result==1) {
		 return	GetMessage.getMessage("회원가입 성공","/am/customerLogin");
		}
		return	GetMessage.getMessage("회원가입 실패","/am/customerRegister");
	}
	
	public int logChk(String id, String pw) {
		customerDTO dto = cm.getCustomer(id);
		int result =1;
		if(dto != null) {
			System.out.println(dto.getcId());
			System.out.println(dto.getcPw());

			if(pw.equals(dto.getcPw())) {
				return 0;
			}
		}
		return result;
	}
	public void keepLogin(String cSessionId, String cId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cSessionId", cSessionId);
		map.put("cId", cId);
		cm.keepLogin( map );
		
	}
	public customerDTO getCustomerSessionId(String cSessionId) {
		return cm.getCustomerSessionId( cSessionId );
	}
	

}
