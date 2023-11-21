package com.care.am.service.reservation;

import java.util.List;
import java.util.Map;
import com.care.am.dto.mediDTO;
import com.care.am.dto.petDTO;
import com.care.am.page.customerPagination;
import com.care.am.page.reservationPagination;
import com.care.am.dto.pageDTO;

public interface reservationService {
	public List<Map<String, mediDTO>> mediList(); //병원 리스트
	public Map<String, Object> mediInfo(String mediName); //병원 상세정보
	public List<String> mediTime(String name); //병원 Time
	public List<Map<String , petDTO>> petList(String id); //사용자 pet lists
	public Map<String, String> reservationCount(Map<String, Object> map); ////시간별 예약자 수 확인
	public List<Map<String, String>> reservationList(String id);
	public List<Map<String, String>> mediSelectList(reservationPagination pag);
	public List<Map<String, String>> customerResList( String id, customerPagination pag);
	public int mediSearch(String mName); //검색한 이름 리스트 갯수 가져오기
	public List<Map<String, String>> mediSelectSearch(String mName, reservationPagination pag); //pag 해당하는 만큼 검색 리스트 가져오기
	public Map<String, String> reservationCheck(Map<String, String> map);
	public Map<String, String> reservationInfo(int rNum); //병원 팝업 예약 정보
	public List<Map<String, Object>> waitList(String mId, int page); //병원 새로운접수 리스트
	public pageDTO waitListPaging(int page, String mId); //병원 새로운 접수 페이징
	public List<Map<String, Object>> ACList(String mId, int page); //병원 승인취소 리스트
	public pageDTO ACListPaging(int page, String mId); //병원 승인취소 페이징
	public int reserCancel(int num);
	public int reserState(int num, int state);

}
