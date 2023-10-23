package com.care.am.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.care.am.dto.mediDTO;

@Mapper
public interface reservationMapper {
	public List<Map<String, mediDTO>> mediList();
	public Map<String, Object> mediInfo(String mediName);
}
