package com.care.am.service.reservation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.am.dto.pageDTO;
import com.care.am.dto.reservationDTO;
import com.care.am.mapper.reservationMapper;
import com.care.am.common.GetMessage;
import com.care.am.dto.mediDTO;
import com.care.am.dto.petDTO;
import com.care.am.mapper.reservationMapper;

@Service
public class reservationServiceImpl implements reservationService{
	
	@Autowired reservationMapper rm;
	
	int pagingLimit = 5; // �������� ������ �� ����
	int blockLimit = 5; // �ϴܿ� ������ ������ ��ȣ ����
	
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
	
	public Map<String, Object> mediInfo(String mediName){ //���� ������
		Map<String, Object> mediInfo = rm.mediInfo(mediName);  //m_addr=13536/��� ������ �д籸 �Ǳ�����2���� 1/3��/
		String addr1 = mediInfo.get("m_addr").toString().split("/")[1];
		String addr2 = mediInfo.get("m_addr").toString().split("/")[2];
		mediInfo.put("m_addr", addr1+" "+addr2);
		
		return mediInfo;
	}
	
	public List<String> mediTime(String name){ //���� time ��������
		Map<String, Object> mediTime = rm.mediTime(name);
		
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
		System.out.println("ser" + rm.petList(id));
		return rm.petList(id);
	}
	
	public int reservationRegister(Map<String, Object> map) { //���� ���� 
		
		String year =  map.get("rDate").toString().replace("�� ", "-");
		String month = year.replace("�� ", "-");
		String day = month.replace("��", "");
		String time = map.get("rTime").toString().replace(":", "-");
		
		Map<String, Object> countMap = new HashMap<String, Object>();
		countMap.put("mName", map.get("mName"));
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
	public String reserCancel(String id, int num) {
		int result = rm.reserCancel(num);
		System.out.println("ser"+result);
		String msg = "", url = "/am/reservationList?id="+id;
		if(result == 1) {
			msg = "������ ��ҵǾ����ϴ�";
		}else {
			msg = "���� ��ҿ� �����Ͽ����ϴ�";
		}
		return GetMessage.getMessage(msg, url);
	}

	@Override
	public int reserState(int num, int state) {
		String apply= "";
		if(state == 1) {
			apply = "Ȯ��";
		}else {
			apply = "���";
		}
		return rm.reserState(apply, num);
	}

	public List<Map<String, Object>> waitList(String mId, int page) { // ���� ���ο����� ����Ʈ

		int pagingStart = (page - 1) * pagingLimit;
		System.out.println("pagingStart"+pagingStart);

		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("start", pagingStart);
		pageMap.put("limit", pagingLimit);
		pageMap.put("mId", mId);

		List<Map<String, Object>> waitList = new ArrayList<Map<String, Object>>();
		waitList = rm.waitList(pageMap);
		
		try {
			for (int i = 0; i < waitList.size(); i++) {
				Map<String, String> map = new HashMap<String, String>();

				waitList.get(i).put("year", waitList.get(i).get("r_date").toString().split("-")[0]);
				waitList.get(i).put("month", waitList.get(i).get("r_date").toString().split("-")[1]);
				waitList.get(i).put("day", waitList.get(i).get("r_date").toString().split("-")[2]);

				waitList.get(i).put("hour", waitList.get(i).get("r_time").toString().split("-")[0]);
				waitList.get(i).put("min", waitList.get(i).get("r_time").toString().split("-")[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return waitList;
	}

	// ���� ���ο����� ����¡
	public pageDTO waitListPaging(int page, String mId) {

		// ��ü �� ���� ��ȸ
		int listCount = rm.waitListPaging(mId); // 13

		// ��ü ������ ���� ���(10/3=3.33333 => 4)
		int maxPage = (int) (Math.ceil((double) listCount / pagingLimit));
		
		// ���� ������ �� ���(1, 4, 7, 10, ~~~~)
		int startPage = (((int) (Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
		
		// �� ������ �� ���(3, 6, 9, 12, ~~~~)
		int endPage = startPage + blockLimit - 1;
		
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		pageDTO pageDTO = new pageDTO();
		pageDTO.setPage(page);
		pageDTO.setMaxPage(maxPage);
		pageDTO.setStartPage(startPage);
		pageDTO.setEndPage(endPage);
		return pageDTO;
	}
	
	public List<Map<String, Object>> ACList(String mId, int page) { // ���� ������� ����Ʈ

		int pagingStart = (page - 1) * pagingLimit;

		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("start", pagingStart);
		pageMap.put("limit", pagingLimit);
		pageMap.put("mId", mId);

		List<Map<String, Object>> ACList = new ArrayList<Map<String, Object>>();
		ACList = rm.ACList(pageMap);
		
		try {
			for (int i = 0; i < ACList.size(); i++) {
				Map<String, String> map = new HashMap<String, String>();

				ACList.get(i).put("year", ACList.get(i).get("r_date").toString().split("-")[0]);
				ACList.get(i).put("month", ACList.get(i).get("r_date").toString().split("-")[1]);
				ACList.get(i).put("day", ACList.get(i).get("r_date").toString().split("-")[2]);

				ACList.get(i).put("hour", ACList.get(i).get("r_time").toString().split("-")[0]);
				ACList.get(i).put("min", ACList.get(i).get("r_time").toString().split("-")[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ACList;

	}

	public pageDTO ACListPaging(int page, String mId) { // ���� ������� ����¡

		// ��ü �� ���� ��ȸ
		int listCount = rm.ACListPaging(mId); // 13
		
		// ��ü ������ ���� ���(10/3=3.33333 => 4)
		int maxPage = (int) (Math.ceil((double) listCount / pagingLimit));
		
		// ���� ������ �� ���(1, 4, 7, 10, ~~~~)
		int startPage = (((int) (Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
		
		// �� ������ �� ���(3, 6, 9, 12, ~~~~)
		int endPage = startPage + blockLimit - 1;
		
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		pageDTO pageDTO = new pageDTO();
		pageDTO.setPage(page);
		pageDTO.setMaxPage(maxPage);
		pageDTO.setStartPage(startPage);
		pageDTO.setEndPage(endPage);
		return pageDTO;
	}

	public Map<String, String> reservationInfo(int rNum) { //���� �˾� ���� ����
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
