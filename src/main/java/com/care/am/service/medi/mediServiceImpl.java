package com.care.am.service.medi;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.am.dto.mediDTO;
import com.care.am.mapper.mediMapper;

@Service
public class mediServiceImpl implements mediService{
	
	@Autowired mediMapper mapper;
	
	public Map<String, Object> getMedi(String id){
		mediDTO dto = mapper.getMedi(id);
	}
}
