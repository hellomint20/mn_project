package com.care.am.service.medi;

import java.util.Map;
import com.care.am.dto.mediDTO;

public interface mediService {
	public String register(mediDTO dto);
	public int logChk(String id, String pw);
	public void keepLogin(String mSessionId, String cId);
	public mediDTO getMediSessionId(String mSessionId);
	public Map<String, Object> getMedi(String id);
	
}
	


