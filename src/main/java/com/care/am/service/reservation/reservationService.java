package com.care.am.service.reservation;

import java.util.List;
import java.util.Map;
import com.care.am.dto.mediDTO;
import com.care.am.dto.petDTO;
import com.care.am.page.customerPagination;
import com.care.am.page.reservationPagination;

import com.care.am.dto.reservationDTO;

public interface reservationService {
	public List<Map<String, mediDTO>> mediList(); //병원 리스트
	public Map<String, Object> mediInfo(String mediId); //병원 상세정보
	public List<String> mediTime(String mediId); //병원 Time
	public List<Map<String , petDTO>> petList(String id); //사용자 pet lists
	public int reservationRegister(Map<String, Object> map); //병원 예약 
	public Map<String, String> reservationCount(Map<String, Object> map); ////시간별 예약자 수 확인
	public List<Map<String, String>> reservationList(String id);
	public String reserCancel(String id, int num);
	public int reserState(int num, int state);
	public List<Map<String, String>> mediReservationList(String id);
	public List<Map<String, String>> mediReservationWaitList(String id);
	public Map<String, String> reservationInfo(int rNum);
	//public Map<String, Object> paging(int num);
	public List<Map<String, String>> mediSelectList(reservationPagination pag);
	public List<Map<String, String>> customerResList( String id, customerPagination pag);
}
