package com.care.am.service.reservation;

import java.util.List;
import java.util.Map;
import com.care.am.dto.mediDTO;
import com.care.am.dto.petDTO;
import com.care.am.dto.reservationDTO;

public interface reservationService {
	public List<Map<String, mediDTO>> mediList(); //병원 리스트
	public Map<String, Object> mediInfo(String mediName); //병원 상세정보
	public List<String> mediTime(String name); //병원 Time
	public List<Map<String , petDTO>> petList(); //사용자 pet list
	public int reservationRegister(Map<String, Object> map); //병원 예약 
	public Map<String, String> reservationCount(Map<String, Object> map); ////시간별 예약자 수 확인
}
