package com.care.am.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface reservationMapper {
	public List<Map<String , Object>> mediReservationList(String mId);
	public List<Map<String, String>> mediReservationWaitList(String mId);
	public Map<String, String> reservationInfo(int rNum);
	
	//public List<Map<String, Object>> pagingList(Map<String, Object> pageMap);
	public List<Map<String, Object>> waitList(Map<String, Object> pageMap); //새로운 접수
	public List<Map<String, Object>> ACList(Map<String, Object> pageMap); //승인취소
	
	
	public Integer mediReservationListCount(String mId);
	
	
	public Integer waitListPaging(String mId);
	public Integer ACListPaging(String mId);
	
	//public int selectListCount();
	//public List<Map<String, Object>> allList(@Param("s") int start, @Param("e") int end);
}