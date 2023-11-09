package com.care.am.service.pet;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.care.am.dto.petDTO;
import com.care.am.dto.typeDTO;

public interface petService {
	public List<petDTO> petList(String id);
	public String petRegister(petDTO dto, MultipartFile file);
	public petDTO petInfo(int num);
	public List<typeDTO> petType(String val);
	public String petDel(int num, String id);
	public String petModify(petDTO dto, MultipartFile file);
}
