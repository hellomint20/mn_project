package com.care.am.service.medi;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.care.am.dto.mediDTO;

public interface mediService {
	public String mediRegister(mediDTO dto,String[] addr);
	public int logChk(String id, String pw);
	public void keepLogin(String mSessionId, String cId);
	public Map<String, Object> getMedi(String id);
	public String mediModify(mediDTO dto, MultipartFile file);
	public mediDTO getMediSessionId(String mSession);
}
	


