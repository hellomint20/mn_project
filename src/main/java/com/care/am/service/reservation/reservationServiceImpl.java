package com.care.am.service.reservation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.am.common.GetMessage;
import com.care.am.dto.reservationDTO;
import com.care.am.mapper.reservationMapper;

@Service
public class reservationServiceImpl implements reservationService{
	
	@Autowired reservationMapper rm;
	
	public List<Map<String, String>> reservationList(String cId) {
		List<Map<String, String>> listmap = new ArrayList<Map<String, String>>();
		listmap = rm.reservationList(cId);
		try {
			for (int i = 0; i <= listmap.size(); i++) {

				Map<String, String> map = new HashMap<String, String>();

				listmap.get(i).put("year", listmap.get(i).get("r_date").split("-")[0]);
				listmap.get(i).put("month", listmap.get(i).get("r_date").split("-")[1]);
				listmap.get(i).put("day", listmap.get(i).get("r_date").split("-")[2]);

				listmap.get(i).put("hour", listmap.get(i).get("r_time").split("-")[0]);
				listmap.get(i).put("min", listmap.get(i).get("r_time").split("-")[1]);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listmap;

	}
		
	@Override
	public String reserCancel(String id, int num) {
		int result = rm.reserCancel(num);
		System.out.println("ser"+result);
		String msg = "", url = "/am/reservationList?id="+id;
		if(result == 1) {
			msg = "예약이 취소되었습니다";
		}else {
			msg = "예약 취소에 실패하였습니다";
		}
		return GetMessage.getMessage(msg, url);
	}

	@Override
	public int reserState(int num, int state) {
		String apply= "";
		if(state == 1) {
			apply = "확정";
		}else {
			apply = "취소";
		}
		return rm.reserState(apply, num);
	}
	
	public List<Map<String, String>> mediReservationList(String mId){
		
		List<Map<String, String>> listMap = new ArrayList<Map<String,String>>();
		listMap = rm.mediReservationList(mId);
		try {
			for(int i =0; i<listMap.size(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				
				listMap.get(i).put("year", listMap.get(i).get("r_date").split("-")[0]);
				listMap.get(i).put("month", listMap.get(i).get("r_date").split("-")[1]);
				listMap.get(i).put("day", listMap.get(i).get("r_date").split("-")[2]);

				listMap.get(i).put("hour", listMap.get(i).get("r_time").split("-")[0]);
				listMap.get(i).put("min", listMap.get(i).get("r_time").split("-")[1]);
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
	
	public List<Map<String, String>> mediReservationWaitList(String mId){
		
		List<Map<String, String>> waitList = new ArrayList<Map<String, String>>();
		waitList = rm.mediReservationWaitList(mId);
		
		
		try {
			for(int i = 0; i<waitList.size(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				
				waitList.get(i).put("year", waitList.get(i).get("r_date").split("-")[0]);
				waitList.get(i).put("month", waitList.get(i).get("r_date").split("-")[1]);
				waitList.get(i).put("day", waitList.get(i).get("r_date").split("-")[2]);
				
				waitList.get(i).put("hour", waitList.get(i).get("r_time").split("-")[0]);
				waitList.get(i).put("min", waitList.get(i).get("r_time").split("-")[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return waitList;
	}
	
	public Map<String, String> reservationInfo(int rNum){
		Map<String, String> info = new HashMap<String, String>();
		info = rm.reservationInfo(rNum);
		
		info.put("hour", info.get("r_time").split("-")[0]);
		info.put("min", info.get("r_time").split("-")[1]);
		
		info.put("year", info.get("r_date").split("-")[0]);
		info.put("month", info.get("r_date").split("-")[1]);
		info.put("day", info.get("r_date").split("-")[2]);
		
		return info;
	}

}