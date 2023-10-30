package com.care.am.mapper;

import java.util.List;
import java.util.Map;


public interface reservationMapper {
	public List<Map<String, String>> reservationList(String cId);
}
