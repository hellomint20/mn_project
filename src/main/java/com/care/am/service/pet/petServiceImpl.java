package com.care.am.service.pet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.am.common.GetMessage;
import com.care.am.dto.petDTO;
import com.care.am.dto.typeDTO;
import com.care.am.mapper.petMapper;

@Service
public class petServiceImpl implements petService {

	@Autowired
	petMapper pm;

	@Override
	public List<petDTO> petList(String id) {
		List<petDTO> list = pm.petList(id);
		return list;
	}

	@Override
	public List<typeDTO> petType() {
		return pm.petType();
	}
	
	@Override
	public String petRegister(petDTO dto) {
		int result = 0;

		try {
			result = pm.petRegister(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result == 1) {
			return GetMessage.getMessage("등록 성공", "/am/pet/petList");
		}
		return GetMessage.getMessage("등록 실패", "/am/pet/petList");
	}

	@Override
	public petDTO petInfo(int num) {
		return pm.petInfo(num);
	}

	

}
