package com.care.am.service.customer;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.care.am.common.GetMessage;
import com.care.am.dto.customerDTO;
import com.care.am.mapper.customerMapper;

@Service
public class customerServiceImpl implements customerService {

	@Autowired
	customerMapper cm;

	BCryptPasswordEncoder encoder;

	public customerServiceImpl() {
		encoder = new BCryptPasswordEncoder();
	}

	public String register(customerDTO dto) {
		int result = 0;

		dto.setcPw(encoder.encode(dto.getcPw()));
		try {
			result = cm.register(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result == 1) {
			return GetMessage.getMessage("ȸ������ ����", "/am/customerLogin");
		}
		return GetMessage.getMessage("ȸ������ ����", "/am/customerRegister");
	}
	
	public String customerSearchId(String inputName, String inputEmail) {
		customerDTO dto = cm.customerSearchId(inputName);
		String result="";
		if(dto!=null) {
			if(inputEmail.equals(dto.getcEmail())){
				result = dto.getcId();
			}
		}
		return result;
	}

	public int logChk(String id, String pw) {
		customerDTO dto = cm.getCustomer(id);
		int result = 0;
		if (dto != null) {
			if (encoder.matches(pw, dto.getcPw()) || pw.equals(dto.getcPw())) {
				result = 1;
			}
		}
		return result;
	}
	public void keepLogin(String cSessionId, String cId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cSessionId", cSessionId);
		map.put("cId", cId);
		cm.keepLogin(map);
	}

	public customerDTO getCustomerInfo(String cId) {
		customerDTO dto = cm.getCustomer(cId);
		return dto;
	}

	public customerDTO getCustomerSessionId(String cSessionId) {
		return cm.getCustomerSessionId(cSessionId);
	}

	public String customerPwdChk(String id, String pw) {
		customerDTO dto = cm.getCustomer(id);
		if (dto != null) {
			if (encoder.matches(pw, dto.getcPw()) || pw.equals(dto.getcPw())) {
				String url = "/am/customerModify?id=" + id;
				return "<script>location.href='" + url + "';</script>";
			}
		}
		return GetMessage.getMessage("��й�ȣ�� Ʋ�Ƚ��ϴ�", "/am/customerPwdChk?id=" + id);
	}

	public String customerPwdChg(customerDTO dto, String pw, String newPw) {
		dto = cm.getCustomer(dto.getcId());
		int result =0;
		if (encoder.matches(pw, dto.getcPw()) || pw.equals(dto.getcPw())) {
				dto.setcPw(encoder.encode(newPw));
				result = cm.customerPwdChg(dto);
				if(result == 1) {
					return GetMessage.getMessage("��й�ȣ�� ����Ǿ����ϴ�", "/am/customerInfo?id=" +dto.getcId());
				}
			}
		return GetMessage.getMessage("��й�ȣ�� Ʋ�Ƚ��ϴ�", "/am/customerPwdChg?id=" + dto.getcId());
	}

	public String customerModify(customerDTO dto) {
		int result = 0;
		result = cm.customerModify(dto);
		if (result == 1) {
			return GetMessage.getMessage("������ �����Ǿ����ϴ�.", "/am/customerInfo?id=" + dto.getcId());
		}
		return GetMessage.getMessage("���������� �����߽��ϴ�.", "/am/customerModify?id=" + dto.getcId());
	}
	public String customerDelete(customerDTO dto, String pw) {
		 dto = cm.getCustomer(dto.getcId());
		 int result =0 ;
			if (encoder.matches(pw, dto.getcPw()) || pw.equals(dto.getcPw())) {
				result =  cm.customerDelete(dto);
				if(result == 1) {
					return GetMessage.getMessage("Ż�� �Ϸ�Ǿ����ϴ�", "/am" );
				}
			}
		return GetMessage.getMessage("��й�ȣ�� Ʋ�Ƚ��ϴ�", "/am/customerPwdChk?id=" + dto.getcId());
	}
}
