package com.care.am.service.medi;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.care.am.dto.mediDTO;
import com.care.am.mapper.mediMapper;

@Service
public class mediServiceImpl implements mediService{

	@Autowired mediMapper mm;
	@Autowired mediFileService mfs;
	
	BCryptPasswordEncoder encoder;
	public mediServiceImpl() {
		encoder = new BCryptPasswordEncoder();
	}
	
	
	public String register(mediDTO dto) {
		return null;
	}

	public int logChk(String id, String pw) {
		mediDTO dto = mm.getMedi(id);
		int result =1;
		if(dto != null) {
			if(encoder.matches(pw, dto.getmPw()) ||
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
	
	public mediDTO getMediSessionId(String mSessionId) {
		return null;
	}
	
	public Map<String, Object> getMedi(String id){  
		mediDTO dto = mm.getMedi(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dto", dto);
		return map;
	}
	
	public String mediModify(mediDTO dto, MultipartFile file) {
		
		String originName = null;
		
		if(!file.isEmpty()) { //수정됨
			originName = dto.getmPhoto();
			dto.setmPhoto(mfs.saveFile(file));
		}
		
		System.out.println("mediService if후");
		
		int result = mm.mediModify(dto);
		
		System.out.println("service결과: " + result );
		
		String msg ="", url="";
		if(result==1) {
			mfs.deleteImage(originName);
			msg = "정보가 수정되었습니다!";
			url = "/am/mediInfo?id=" + dto.getmId();
		}else {
			mfs.deleteImage(dto.getmPhoto());
			msg = "정보 수정에 실패했습니다.";
			url = "/am/mediInfo?id=" + dto.getmId();
		}
		return mfs.getMessage(msg, url);
	}

}
