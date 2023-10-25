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
		dto.setmAddr(ad); //합쳐진 주소 dto에 넣어줘
		dto.setmPw((encoder.encode(dto.getmPw()))); //비밀번호 암호화해서 저장
		
		int result =0;
		try {
			result = mm.mediRegister(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result==1) {
			return GetMessage.getMessage("회원가입 성공", "/am/mediLogin");
		}
		else {
			return GetMessage.getMessage("회원가입 실패", "/am/mediRegister");
		}
	}

	public int logChk(String id, String pw) {
		mediDTO dto = mm.getMedi(id);
		int result =1;
		if(dto != null) {
			
			if(encoder.matches(pw, dto.getmPw()) || // 사용자가 입력한 평문이랑 암호화된 비밀번호 비교
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
