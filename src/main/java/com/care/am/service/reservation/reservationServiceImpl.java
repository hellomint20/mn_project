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
	
	public List<Map<String, mediDTO>> mediList(){ //���� ����Ʈ 
		return rm.mediList();
	}
	public Map<String, Object> mediInfo(String mediName){ //���� ������
		Map<String, Object> mediInfo = rm.mediInfo(mediName);  //m_addr=13536/��� ������ �д籸 �Ǳ�����2���� 1/3��/
		String addr1 = mediInfo.get("m_addr").toString().split("/")[1];
		String addr2 = mediInfo.get("m_addr").toString().split("/")[2];
		mediInfo.put("m_addr", addr1+" "+addr2);
		
		return mediInfo;
	}
	
	public List<String> mediTime(String name){ //���� time ��������
		Map<String, Object> mediTime = rm.mediTime(name);
		System.out.println(mediTime);
		
		System.out.println("==============================");
		
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
		System.out.println(timeList);

		return timeList;
	}
	
	public List<Map<String, petDTO>> petList(String id) { //����� pet list
		System.out.println("ser" + rm.petList(id));
		return rm.petList(id);
	}
	public int reservationRegister(Map<String, Object> map) { //���� ���� 
		int result = 0;
		System.out.println(map);
		
		
		String year =  map.get("rDate").toString().replace("�� ", "-");
		String month = year.replace("�� ", "-");
		String day = month.replace("��", "");
		map.put("rDate", day);
		
		map.put("rTime", map.get("rTime").toString().replace(":", "-"));
		
		System.out.println(map);
		result = rm.reservationRegister(map);
		return result;
	}
}
