package com.care.am.mapper;

import java.util.List;
import java.util.Map;

import com.care.am.dto.reservationDTO;


public interface reservationMapper {
	public List<Map<String, String>> reservationList(String cId);
	public int reserCancel(int num);
	public int reserState(int num);
}
