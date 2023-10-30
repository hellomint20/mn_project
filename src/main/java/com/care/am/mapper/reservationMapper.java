package com.care.am.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.care.am.dto.mediDTO;
import com.care.am.dto.petDTO;

@Mapper
public interface reservationMapper {
	public List<Map<String, mediDTO>> mediList(); //���� ����Ʈ
	public Map<String, Object> mediInfo(String mediName); //���� ������
	public Map<String, Object> mediTime(String name); //���� Time
	public List<Map<String, petDTO>> petList(String id); //����� pet list
	public List<Map<String, petDTO>> petList(); //����� pet list
	public int reservationRegister(Map<String, Object> map); //���� ���� 
}
