package com.care.am.service.reservation;

import java.util.List;
import java.util.Map;

import com.care.am.dto.reservationDTO;

public interface reservationService {
	public List<Map<String, String>> reservationList(String id);
	public String reserCancel(String id, int num);
	public int reserState(int num, int state);
	public List<Map<String, String>> mediReservationList(String id);
	public List<Map<String, String>> mediReservationWaitList(String id);
	public Map<String, String> reservationInfo(int rNum);
	//public Map<String, Object> paging(int num);
}
