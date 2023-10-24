package com.care.am.mapper;

import com.care.am.dto.mediDTO;
import java.util.Map;

public interface mediMapper {
	public mediDTO getMedi(String id);
	public void keepLogin(Map<String, Object> map);
	public int register(mediDTO dto);
	public mediDTO getMediSession( String mSession );
		
}
