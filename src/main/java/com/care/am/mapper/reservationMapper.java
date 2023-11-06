package com.care.am.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

import com.care.am.dto.mediDTO;
import com.care.am.dto.petDTO;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface reservationMapper {
	//public List<Map<String , Object>> mediReservationList(String mId);
	
	public List<Map<String, String>> reservationList(String cId);
	public List<Map<String, mediDTO>> mediList(); //���� ����Ʈ
	public Map<String, Object> mediInfo(String mediName); //���� ������
	public Map<String, Object> mediTime(String name); //���� Time
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
	
	
	
	//public List<Map<String, Object>> pagingList(Map<String, Object> pageMap);
	public List<Map<String, Object>> waitList(Map<String, Object> pageMap); //���ο� ����
	public List<Map<String, Object>> ACList(Map<String, Object> pageMap); //�������
	
	
	public Integer mediReservationListCount(String mId);
	
	
	public Integer waitListPaging(String mId);
	public Integer ACListPaging(String mId);
	
}
