package com.care.am.service.reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.am.dto.mediDTO;
import com.care.am.dto.petDTO;
import com.care.am.mapper.reservationMapper;

@Service
public class reservationServiceImpl implements reservationService{
	@Autowired
	reservationMapper rm;
	
	public List<Map<String, mediDTO>> mediList(){ //병원 리스트 
		return rm.mediList();
	}
	public Map<String, Object> mediInfo(String mediName){ //병원 상세정보
		Map<String, Object> mediInfo = rm.mediInfo(mediName);  //m_addr=13536/경기 성남시 분당구 판교역로2번길 1/3층/
		String addr1 = mediInfo.get("m_addr").toString().split("/")[1];
		String addr2 = mediInfo.get("m_addr").toString().split("/")[2];
		mediInfo.put("m_addr", addr1+" "+addr2);
		
		return mediInfo;
	}
	
	public List<String> mediTime(String name){ //병원 time 가져오기
		Map<String, Object> mediTime = rm.mediTime(name);
		System.out.println(mediTime);
		
		System.out.println("==============================");
		
		//시간을 list로 담기
		List<String > timeList = new ArrayList<String>();
		
		String openTime = mediTime.get("open_time").toString();
		String lunchStartTime = mediTime.get("lunch_start_time").toString();
		String lunchEndTime = mediTime.get("lunch_end_time").toString();
		String closeTime = mediTime.get("close_time").toString();
		
		//open - lunch_start
		for(int i=Integer.parseInt(openTime.split(":")[0]); i<Integer.parseInt(lunchStartTime.split(":")[0]); i++) {
			timeList.add(String.valueOf(String.format("%02d", i))+":"+mediTime.get("open_time").toString().split(":")[1]);
		}
		//lunch_end - close
		for(int i=Integer.parseInt(lunchEndTime.split(":")[0]); i<Integer.parseInt(closeTime.split(":")[0]); i++) {
			timeList.add(String.valueOf(String.format("%02d", i))+":"+mediTime.get("lunch_end_time").toString().split(":")[1]);
		}
		System.out.println(timeList);

		return timeList;
	}
	
	public List<Map<String, petDTO>> petList(String id) { //사용자 pet list
		System.out.println("ser" + rm.petList(id));
		return rm.petList(id);
	}
	public int reservationRegister(Map<String, Object> map) { //병원 예약 
		int result = 0;
		System.out.println(map);
		
		
		String year =  map.get("rDate").toString().replace("년 ", "-");
		String month = year.replace("월 ", "-");
		String day = month.replace("일", "");
		map.put("rDate", day);
		
		map.put("rTime", map.get("rTime").toString().replace(":", "-"));
		
		System.out.println(map);
		result = rm.reservationRegister(map);
		return result;
	}
}
