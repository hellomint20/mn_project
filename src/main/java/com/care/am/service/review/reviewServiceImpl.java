package com.care.am.service.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.am.common.GetMessage;
import com.care.am.dto.reviewDTO;
import com.care.am.mapper.reviewMapper;

@Service
public class reviewServiceImpl implements reviewService{

	@Autowired reviewMapper bm;
	
	@Override
	public List<reviewDTO> boardList() {
		return bm.boardList();
	}

	@Override
	public List<reviewDTO> reviewList(String id) {
		return bm.reviewList(id);
	}
	
	public String fixedForm(reviewDTO dto, int num) {
		int result =0;
		
		result = bm.fixedForm(dto);
		
		if(result == 1) {
			return GetMessage.getMessage("���䰡 ��ϵǾ����ϴ�!", "/am/fixedForm?num="+num); //���߿� ��� ����
		}
		return GetMessage.getMessage("���� ��Ͽ� �����߽��ϴ�.", "/am/fixedForm");
	}
	

}
