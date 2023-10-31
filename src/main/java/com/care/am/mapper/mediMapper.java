package com.care.am.mapper;

import com.care.am.dto.mediDTO;
import java.util.Map;

public interface mediMapper {
	public void keepLogin(Map<String, Object> map);
	public int mediRegister(mediDTO dto);
	public mediDTO getMediSession( String mSession );
	public mediDTO getMedi(String id);	
	public int mediModify(mediDTO dto);
	public int mediPwdChg(mediDTO dto);
		
}
