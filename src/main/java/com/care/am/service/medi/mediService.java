package com.care.am.service.medi;

import com.care.am.dto.mediDTO;

public interface mediService {
	public String mediRegister(mediDTO dto,String[] addr);
	public int logChk(String id, String pw);
	public void keepLogin(String mSessionId, String cId);
	public mediDTO getMediSessionId(String mSession);
	public String mediSearchId(String inputName, String inputTel);
	public mediDTO mediSearchPw(String inputId, String inputName, String inputTel);
	public String mediPwChg(String newPw, String id);
}
