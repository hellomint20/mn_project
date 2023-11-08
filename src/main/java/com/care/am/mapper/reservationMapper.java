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
	public int mediSearch(String mName); //�˻��� �̸� ����Ʈ ���� ��������
	public List<Map<String, String>> mediSelectSearch(@Param("mName") String mName, @Param("start") String start, @Param("end") String end); //pag �ش��ϴ� ��ŭ �˻� ����Ʈ ��������
	public Map<String, Object> mediInfo(String mediId); //���� ������
	public Map<String, Object> mediTime(String mediId); //���� Time
	public List<Map<String, petDTO>> petList(String id); //����� pet list
	public List<Map<String, petDTO>> petList(); //����� pet list
	public int reservationRegister(Map<String, Object> map); //���� ���� 
	public List<Map<String, Object>> reservationCount(Map<String, Object> map); ////�ð��� ������ �� Ȯ��
	public Map<String, Object> peopleCount(Map<String, Object> map);
	public List<Map<String, String>> mediSelectList(reservationPagination pag);
	public List<Map<String, String>> customerResList(@Param("id") String id, @Param("start") String start, @Param("end") String end);
	public Map<String, String> reservationCheck(Map<String, String> map);
}
