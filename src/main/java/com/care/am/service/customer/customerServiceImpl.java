package com.care.am.service.customer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.care.am.common.GetMessage;
import com.care.am.dto.customerDTO;
import com.care.am.mapper.customerMapper;

@Service
public class customerServiceImpl implements customerService{
	
	@Autowired customerMapper cm;
	
	BCryptPasswordEncoder encoder;
	public customerServiceImpl() {
		encoder = new BCryptPasswordEncoder();
	}
	
	public String register(customerDTO dto) {
		int result=0;
		
		dto.setcPw( encoder.encode(dto.getcPw()) );
		try {
			result = cm.register( dto );
		} catch (Exception e) {
			e.printStackTrace();
		}
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

			if( encoder.matches(pw, dto.getcPw() ) 
					|| pw.equals( dto.getcPw() ) ) {
			System.out.println("로그인확인성공");
			result = 0;
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
	
	public customerDTO getCustomerInfo(String cId) {
		customerDTO dto = cm.getCustomer(cId);
		return dto;
		
	}
	
	public customerDTO getCustomerSessionId(String cSessionId) {
		return cm.getCustomerSessionId( cSessionId );
	}
	
	public String customerModify(customerDTO dto) {
		int result = cm.customerModify(dto);
		
		dto.setcPw( encoder.encode(dto.getcPw()) );
		if(result ==1) {
			
			return GetMessage.getMessage("정보가 수정되었습니다.", "/am/customerInfo");
		}
		else {
			return GetMessage.getMessage("정보수정에 실패했습니다.", "/am/customerModify");
		}
	}
	

}
