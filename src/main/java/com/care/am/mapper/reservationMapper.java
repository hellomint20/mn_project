package com.care.am.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.care.am.dto.mediDTO;
import com.care.am.dto.petDTO;

@Mapper
public interface reservationMapper {
	public List<Map<String, mediDTO>> mediList(); //병원 리스트
	public Map<String, Object> mediInfo(String mediName); //병원 상세정보
	public Map<String, Object> mediTime(String name); //병원 Time
	public List<Map<String, petDTO>> petList(String id); //사용자 pet list
	public List<Map<String, petDTO>> petList(); //사용자 pet list
	public int reservationRegister(Map<String, Object> map); //병원 예약 
}
