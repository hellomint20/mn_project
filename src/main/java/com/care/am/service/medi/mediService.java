package com.care.am.service.medi;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.care.am.dto.mediDTO;

public interface mediService {
	public String mediRegister(mediDTO dto,String[] addr);
	public boolean mediIdCheck(String id);
	public int logChk(String id, String pw);
	public void keepLogin(String mSessionId, String cId);
	public Map<String, Object> getMedi(String id);
	public String mediModify(mediDTO dto, MultipartFile fileName, String[] addr);
	public mediDTO getMediSessionId(String mSession);
	public String mediSearchId(String inputName, String inputTel);
	public mediDTO mediSearchPw(String inputId, String inputName, String inputTel);
	public String mediNewPwd(String newPw, String id);
	public String mediPwdChk(String id, String pw);
	public String mediPwdChg(mediDTO dto,String pw, String newPw);
	public String mediDelete(mediDTO dto, String pw);
}
	


