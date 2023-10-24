package com.care.am.service.medi;

import java.util.HashMap;
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
		//System.out.println(dto.getmId());
		//System.out.println(dto.getmPw());
		//System.out.println(dto.getmName());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dto", dto);
		
		return map;
	}
}
