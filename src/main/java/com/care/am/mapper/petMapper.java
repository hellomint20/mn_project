package com.care.am.mapper;

import java.util.List;

import com.care.am.dto.petDTO;
import com.care.am.dto.typeDTO;

public interface petMapper {
	public List<petDTO> petList(String id);
	public int petRegister(petDTO dto);
	public petDTO petInfo(int num);
	public List<typeDTO> petType();
}
