package com.care.am.service.reservation;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.am.mapper.reservationMapper;

@Service
public class reservationServiceImpl implements reservationService{

	@Autowired reservationMapper rm;
	
	public Map<String,String> reservationList(String cId) {
		Map<String,String> map = rm.reservationList(cId);
		System.out.println(map);
		String[] rDate = map.get("r_date").split("-");
		if(rDate.length>1) {
			map.put("year",rDate[0]);
			map.put("month",rDate[1]);
			map.put("day",rDate[2]);
		}
		String[] rTime = map.get("r_time").split("-");
		if(rTime.length>1) {
			map.put("hour", rTime[0]);
			map.put("min",rTime[1]);
		}
		return map;
	}
}
