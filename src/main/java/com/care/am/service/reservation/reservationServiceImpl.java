package com.care.am.service.reservation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.am.dto.reservationDTO;
import com.care.am.mapper.reservationMapper;

@Service
public class reservationServiceImpl implements reservationService{
	
	@Autowired reservationMapper rm;
	
	public List<Map<String, String>> mediReservationList(String mId){
		
		List<Map<String, String>> listMap = new ArrayList<Map<String,String>>();
		listMap = rm.mediReservationList(mId);
		
		try {
			for(int i =0; i<=listMap.size(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				
				listMap.get(i).put("date", listMap.get(i).get("r_date"));
				listMap.get(i).put("hour", listMap.get(i).get("r_time").split("-")[0]);
				listMap.get(i).put("min", listMap.get(i).get("r_time").split("-")[1]);
				listMap.get(i).put("type", listMap.get(i).get("p_type"));
				listMap.get(i).put("content", listMap.get(i).get("r_content"));
				listMap.get(i).put("apply", listMap.get(i).get("r_apply"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listMap;
	}
	/*
	public Map<String, Object> paging(int num){
		int listSize = 6; //몇 개 글
		int allCount = rm.selectListCount(); //글 총 개수
		int page = allCount / listSize; //총 페이지 수
		if(allCount % listSize != 0) {
			page++;
		}
		
		int end = num * listSize;
		int start = end + 1 - listSize;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", rm.allList(start,end));
	}*/
}
