package com.care.am.service.pet;

import java.util.List;

import com.care.am.dto.petDTO;
import com.care.am.dto.typeDTO;

public interface petService {
	public List<petDTO> petList(String id);
	public String petRegister(petDTO dto);
	public petDTO petInfo(int num);
	public List<typeDTO> petType();
}
