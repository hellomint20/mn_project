package com.care.am.service.pet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.care.am.common.GetMessage;
import com.care.am.dto.petDTO;
import com.care.am.dto.typeDTO;
import com.care.am.mapper.petMapper;

@Service
public class petServiceImpl implements petService {

	@Autowired	petMapper pm;
	@Autowired	petFileService pfs;

	@Override
	public List<petDTO> petList(String id) {
		List<petDTO> list = pm.petList(id);
		return list;
	}

	@Override
	public List<typeDTO> petType(String val) {
		return pm.petType(val);
	}
	
	@Override
	public String petRegister(petDTO dto, MultipartFile file) {
		String originName = file.getOriginalFilename();
		
		if(originName != "") {
			dto.setpPhoto(pfs.saveFile(file));
		}
		int result = pm.petRegister(dto);
		
		if(result==1) {
			return GetMessage.getMessage("��� ����", "/am/pet/petList?id="+dto.getcId());
		}else {
			pfs.deleteImage(dto.getpPhoto());
			return GetMessage.getMessage("��� ����", "/am/pet/petRegister?id="+dto.getcId());
		}
	}

	@Override
	public petDTO petInfo(int num) {
		return pm.petInfo(num);
	}

	@Override
	public String petDel(int num, String id) {
		int result = 1;
		String dbImg = pm.petInfo(num).getpPhoto();
		
		result = pm.petDel(num);
		
		if (result == 1) {
			if(dbImg != null) {
	            pfs.deleteImage(dbImg);
	         }
			return GetMessage.getMessage("���� ����", "petList?id="+id);
		}
		else {
			return GetMessage.getMessage("���� ����", "petList?id="+id);
		}
	}

	@Override
	public String petModify(petDTO dto, MultipartFile file) {
		String dbImg = pm.petInfo(dto.getpNum()).getpPhoto();	// ������ �ִ� �̹���
		String originName = file.getOriginalFilename();		// ���� ���� �̹���
		
		if(originName == "") {
			dto.setpPhoto(dbImg);
		}else {
			dto.setpPhoto(pfs.saveFile(file));
	        pfs.deleteImage(dbImg);
		}
		
		int result = pm.petModify(dto);
		
		if(result==1) {
			return GetMessage.getMessage("������ �����Ǿ����ϴ�!", "/am/pet/petList?id=" + dto.getcId());
		}else {
			pfs.deleteImage(dto.getpPhoto());
			return GetMessage.getMessage("���� ������ �����߽��ϴ�.", "/am/pet/petList?id=" + dto.getcId());
		}
	}

}
