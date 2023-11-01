package com.care.am.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface reservationMapper {
	public List<Map<String , String>> mediReservationList(String mId);
	public List<Map<String, String>> mediReservationWaitList(String mId);
	public Map<String, String> reservationInfo(int rNum);
	//public int selectListCount();
	//public List<Map<String, Object>> allList(@Param("s") int start, @Param("e") int end);
}