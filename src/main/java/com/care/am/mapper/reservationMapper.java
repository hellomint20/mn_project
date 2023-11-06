package com.care.am.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.care.am.dto.mediDTO;
import com.care.am.dto.petDTO;
import com.care.am.page.reservationPagination;

@Mapper
public interface reservationMapper {
	public List<Map<String, String>> reservationList(String cId);
	public List<Map<String, mediDTO>> mediList(); //���� ����Ʈ
	public Map<String, Object> mediInfo(String mediId); //���� ������
	public Map<String, Object> mediTime(String mediId); //���� Time
	public List<Map<String, petDTO>> petList(String id); //����� pet list
	public List<Map<String, petDTO>> petList(); //����� pet list
	public int reservationRegister(Map<String, Object> map); //���� ���� 
	public List<Map<String, Object>> reservationCount(Map<String, Object> map); ////�ð��� ������ �� Ȯ��
	public Map<String, Object> peopleCount(Map<String, Object> map);
	public int reserCancel(int num);
	public int reserState(@Param("apply") String apply, @Param("num") int num);
	public List<Map<String , String>> mediReservationList(String mId);
	public List<Map<String, String>> mediReservationWaitList(String mId);
	public Map<String, String> reservationInfo(int rNum);
	//public int selectListCount();
	//public List<Map<String, Object>> allList(@Param("s") int start, @Param("e") int end);
	public List<Map<String, String>> mediSelectList(reservationPagination pag);
	public List<Map<String, String>> customerResList(@Param("id") String id, @Param("start") String start, @Param("end") String end);
}
