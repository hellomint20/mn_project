package com.care.am.service.reservation;

import java.util.List;
import java.util.Map;

import com.care.am.dto.pageDTO;

public interface reservationService {
	public List<Map<String, Object>> mediReservationList(String id, int page);
	public List<Map<String, String>> mediReservationWaitList(String id);
	public Map<String, String> reservationInfo(int rNum);
	
	//다되면 지우기
	public List<Map<String, Object>> paging(int page, String id);
	public pageDTO pagingParam(int page, String id);//여까지
	
	public List<Map<String, Object>> waitList(String mId, int page); //새로운접수
	public pageDTO waitListPaging(int page, String mId); //새로운 접수 페이징
	
	public List<Map<String, Object>> ACList(String mId, int page); //승인취소
	public pageDTO ACListPaging(int page, String mId); //승인취소 페이징
}
