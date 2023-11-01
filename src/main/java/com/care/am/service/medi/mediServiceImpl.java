package com.care.am.service.medi;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.care.am.common.GetMessage;
import com.care.am.dto.mediDTO;
import com.care.am.mapper.mediMapper;


@Service
public class mediServiceImpl implements mediService{

	@Autowired mediMapper mm;
	
	BCryptPasswordEncoder encoder;
	public mediServiceImpl() {
		encoder = new BCryptPasswordEncoder();
	}
	
	
	public String mediRegister(mediDTO dto, String[] addr) {
		String ad ="";
		for(String a:addr) {
			ad += a+"/";
		}
		dto.setmAddr(ad); //������ �ּ� dto�� �־���
		dto.setmPw((encoder.encode(dto.getmPw()))); //��й�ȣ ��ȣȭ�ؼ� ����
		
		int result =0;
		try {
			result = mm.mediRegister(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result==1) {
			return GetMessage.getMessage("ȸ������ ����", "/am/mediLogin");
		}
		else {
			return GetMessage.getMessage("ȸ������ ����", "/am/mediRegister");
		}
	}

	public int logChk(String id, String pw) {
		mediDTO dto = mm.getMedi(id);
		int result =1;
		if(dto != null) {
			
			if(encoder.matches(pw, dto.getmPw()) || // ����ڰ� �Է��� ���̶� ��ȣȭ�� ��й�ȣ ��
					pw.equals(dto.getmPw())) {
				result =0;
			}
		}
		return result;
	}
	
	public void keepLogin(String mSession, String mId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mSessionId", mSession);
		map.put("mId", mId);
		mm.keepLogin( map );
		
	}
	
	public mediDTO getMediSessionId(String mSession) {
		return mm.getMediSession(mSession);
	}
}
