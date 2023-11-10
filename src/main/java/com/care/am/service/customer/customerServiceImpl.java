package com.care.am.service.customer;

import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.care.am.common.GetMessage;
import com.care.am.dto.customerDTO;
import com.care.am.mapper.customerMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

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
			return GetMessage.getMessage("회원가입 성공", "/am/customerLogin");
		}
		return GetMessage.getMessage("회원가입 실패", "/am/customerRegister");
	}
	
	public customerDTO naverLogin(String apiResult) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> map = new HashMap<>();
		Map<String, Object> data = mapper.readValue(apiResult, Map.class);
		JSONParser jsonParser = new JSONParser();
		
		JSONObject jsonObject = (JSONObject) jsonParser.parse(apiResult);
		jsonObject = (JSONObject) jsonObject.get("response");
		map.put("cEmail", jsonObject.get("email"));
		map.put("cName", jsonObject.get("name"));
		map.put("cTel", jsonObject.get("mobile"));
		
		String email = (String) ((Map<String, Object>) data.get("response")).get("email");
		String id = email.split("@")[0]+"_naver";
		customerDTO dto = new customerDTO(); 
		dto = cm.getCustomer(id);
		System.out.println("dto"+dto);
		String pwd = makeRandomPw();
		if(dto == null) { // 네이버 아이디로 회원가입된 정보가 없다면
			customerDTO ndto = new customerDTO();
			ndto.setcId(id);
			ndto.setcName(map.get("cName").toString());
			ndto.setcEmail(map.get("cEmail").toString()); 
			ndto.setcTel(map.get("cTel").toString());
			ndto.setcPw(encoder.encode(pwd));
			cm.register(ndto);
			return ndto;
		}
		
		return dto;
	}
	
	public customerDTO googleLogin(String profile) {
		System.out.println(profile);
		
		return null;
	}
	
	public String customerSearchId(String inputName, String inputEmail) {
		customerDTO dto = cm.customerSearchId(inputName, inputEmail);
		String result="";
		if(dto!=null) {
				result = dto.getcId();
		}
		return result;
	}

	public customerDTO customerSearchPw(String inputId, String inputName, String inputTel) {
			customerDTO dto = cm.getCustomer(inputId);
			if(dto!=null) {
				if(inputName.equals(dto.getcName()) && inputTel.equals(dto.getcTel())) {
					System.out.println("아이디, 이름, 전화번호 다 일치 > 임시비밀번호 발급");
					return dto;
				}
			}
			return null;
	}
	
	public String makeRandomPw() {
		StringBuffer sb = new StringBuffer();
		SecureRandom sr = new SecureRandom();
		char[] charSet = new char[] {
	                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
	                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
	                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
	                'U', 'V', 'W', 'X', 'Y', 'Z','a', 'b', 'c', 'd', 
	                'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 
	                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
	                '!', '@', '#', '$', '%', '^', '&' };
		sr.setSeed(new Date().getTime());
		int idx = 0;
		int len = charSet.length;
		for(int i=0; i<8;i++) { // 8은 임시비밀번호 자리수
			idx = sr.nextInt(len);
			sb.append(charSet[idx]);
			}
			return sb.toString();
		}
	
	public int customerPwChg(String tempPwd, customerDTO dto) {
		dto.setcPw(encoder.encode(tempPwd));
		int result = cm.customerPwChg(dto);
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
		return GetMessage.getMessage("비밀번호가 틀렸습니다", "/am/customerPwdChk?id=" + id);
	}

	public String customerPwdChg(customerDTO dto, String pw, String newPw) {
		dto = cm.getCustomer(dto.getcId());
		int result =0;
		if (encoder.matches(pw, dto.getcPw()) || pw.equals(dto.getcPw())) {
				dto.setcPw(encoder.encode(newPw));
				result = cm.customerPwdChg(dto);
				if(result == 1) {
					return GetMessage.getMessage("비밀번호가 변경되었습니다", "/am/customerInfo?id=" +dto.getcId());
				}
			}
		return GetMessage.getMessage("비밀번호가 틀렸습니다", "/am/customerPwdChg?id=" + dto.getcId());
	}

	public String customerModify(customerDTO dto) {
		int result = 0;
		result = cm.customerModify(dto);
		if (result == 1) {
			return GetMessage.getMessage("정보가 수정되었습니다.", "/am/customerInfo?id=" + dto.getcId());
		}
		return GetMessage.getMessage("정보수정에 실패했습니다.", "/am/customerModify?id=" + dto.getcId());
	}
	public String customerDelete(customerDTO dto, String pw) {
		 dto = cm.getCustomer(dto.getcId());
		 int result =0 ;
			if (encoder.matches(pw, dto.getcPw()) || pw.equals(dto.getcPw())) {
				result =  cm.customerDelete(dto);
				if(result == 1) {
					return GetMessage.getMessage("탈퇴가 완료되었습니다", "/am" );
				}
			}
		return GetMessage.getMessage("비밀번호가 틀렸습니다", "/am/customerPwdChk?id=" + dto.getcId());
	}
	

	

}