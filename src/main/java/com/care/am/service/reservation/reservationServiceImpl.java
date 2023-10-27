package com.care.am.service.reservation;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.am.dto.reservationDTO;
import com.care.am.mapper.reservationMapper;

@Service
public class reservationServiceImpl implements reservationService{
	
	@Autowired reservationMapper rm;
	
	public Map<String, Object> getList(String id){
		reservationDTO dto = rm.getList(id);
	}
}
