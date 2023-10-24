package com.care.am.service.medi;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.care.am.dto.mediDTO;
import com.care.am.mapper.mediMapper;


@Service
public class mediServiceImpl implements mediService{

	@Autowired mediMapper mm;
	
	BCryptPasswordEncoder encoder;
	public mediServiceImpl() {
		encoder = new BCryptPasswordEncoder();
	}
	
	
	public String register(mediDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	public int logChk(String id, String pw) {
		mediDTO dto = mm.getMedi(id);
		int result =1;
		if(dto != null) {
			System.out.println(dto.getmId());
			System.out.println(dto.getmPw());
			
			if(encoder.matches(pw, dto.getmPw()) ||
					pw.equals(dto.getmPw())) {
				System.out.println("병원 로그인 성공");
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
	
	public mediDTO getMediSessionId(String mSessionId) {
		// TODO Auto-generated method stub
		return null;
	}
}
