package com.care.am.mapper;

import java.util.Map;

import com.care.am.dto.mediDTO;

public interface mediMapper {
	public mediDTO getMedi(String id);
	public void keepLogin(Map<String, Object> map);
	public int mediRegister(mediDTO dto);
	public mediDTO getMediSession( String mSession );
		
}
