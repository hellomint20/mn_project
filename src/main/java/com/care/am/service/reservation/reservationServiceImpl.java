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
public class reservationServiceImpl implements reservationService {

	@Autowired
	reservationMapper rm;

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
	public int reserState(int num) {
		return rm.reserState(num);
	}
}
