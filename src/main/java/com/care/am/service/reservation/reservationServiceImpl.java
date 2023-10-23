package com.care.am.service.reservation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.am.dto.mediDTO;
import com.care.am.dto.reservationDTO;
import com.care.am.mapper.reservationMapper;

@Service
public class reservationServiceImpl implements reservationService{
	@Autowired
	reservationMapper rm;
	
	public List<Map<String, mediDTO>> mediList(){
		return rm.mediList();
	}
	public Map<String, Object> mediInfo(String mediName){
		System.out.println("ser"+mediName);
		System.out.println("ser"+rm.mediInfo(mediName));
		return rm.mediInfo(mediName);
	}
}
