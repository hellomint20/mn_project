package com.care.am.service.reservation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.am.dto.mediDTO;
import com.care.am.dto.petDTO;
import com.care.am.mapper.reservationMapper;
import com.care.am.page.reservationPagination;

@Service
public class reservationServiceImpl implements reservationService {

	@Autowired
	reservationMapper rm;

	public List<Map<String, String>> reservationList(String cId) {
		List<Map<String, String>> listmap = new ArrayList<Map<String, String>>();
		listmap = rm.reservationList(cId);
		try {
			for (int i = 0; i <= listmap.size(); i++) {
				
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
	
	public List<Map<String, mediDTO>> mediList(){ //���� ����Ʈ 
		return rm.mediList();
	}
	public Map<String, Object> mediInfo(String mediId){ //���� ������
		Map<String, Object> mediInfo = rm.mediInfo(mediId);  
		String addr1 = mediInfo.get("m_addr").toString().split("/")[1];
		String addr2 = mediInfo.get("m_addr").toString().split("/")[2];
		mediInfo.put("m_addr", addr1+" "+addr2);
		return mediInfo;
	}

	public List<String> mediTime(String mediId){ //���� time ��������
		Map<String, Object> mediTime = rm.mediTime(mediId);
		
		//�ð��� list�� ���
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
		return timeList;
	}
	
	public List<Map<String, petDTO>> petList(String id) { //����� pet list
		return rm.petList(id);
	}
	
	public int reservationRegister(Map<String, Object> map) { //���� ���� 
		
		String year =  map.get("rDate").toString().replace("�� ", "-");
		String month = year.replace("�� ", "-");
		String day = month.replace("��", "");
		String time = map.get("rTime").toString().replace(":", "-");
		
		Map<String, Object> countMap = new HashMap<String, Object>();
		countMap.put("mId", map.get("mId"));
		countMap.put("rDate", day);
		countMap.put("rTime", time);
		System.out.println("register" + countMap);
		
		int result = 0;
		
		Integer.parseInt(String.valueOf(rm.peopleCount(countMap).get("count(*)")));
		System.out.println(Integer.parseInt(String.valueOf(rm.peopleCount(countMap).get("count(*)"))));
		if(Integer.parseInt(String.valueOf(rm.peopleCount(countMap).get("count(*)"))) >= 3) {
			result = 99;
		}else {
			map.put("rDate", day);
			map.put("rTime", time);
			result = rm.reservationRegister(map);
		}		

		return result;
	} 
	
	public Map<String, String> reservationCount(Map<String, Object> map) { ////�ð��� ������ �� Ȯ��
	
		System.out.println("count"+map);
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list = rm.reservationCount(map);
		
		Map<String, String> rDateCount = new HashMap<String, String>();
		
		for(int i=0; i<list.size(); i++) {
			String rTime = list.get(i).get("r_time").toString().split("-")[0]+":"+list.get(i).get("r_time").toString().split("-")[1];
			rDateCount.put(rTime, list.get(i).get("count(*)").toString());
		}	
		return rDateCount; 
	}

	@Override
	public List<Map<String, String>> mediSelectList(reservationPagination pag) {
		return rm.mediSelectList(pag);
	}
}
