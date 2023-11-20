package com.care.am.service.review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.am.common.GetMessage;
import com.care.am.dto.pageDTO;
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
	
	public reviewDTO myReview(int num){
		reviewDTO dto = new reviewDTO();
		try {
			dto = bm.myReview(num);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(dto);
		return dto;
	}
	
	
	public String fixedForm(reviewDTO dto, String id, int num) {
		int result =0;
		int fixResult = 0;
		
		result = bm.fixedForm(dto);
		if(result==1) {
			try {
				fixResult = bm.fixResult(num);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(fixResult == 1) {
			return GetMessage.getMessage("���䰡 ��ϵǾ����ϴ�!", "/am/reviewList?id="+id);
		}
		return GetMessage.getMessage("���� ��Ͽ� �����߽��ϴ�.", "/am/reservationList?id="+id);
	}
	
	public String modiForm(reviewDTO dto) {
		int result = 0;
		result = bm.modiForm(dto);
		if(result ==1) {
			return GetMessage.getMessage("���䰡 �����Ǿ����ϴ�.", "/am/myReview?num="+dto.getRvNo());
		}
		return GetMessage.getMessage("���� ������ �����߽��ϴ�.", "/am/modiForm?num="+dto.getRvNo());
	}
	
	public String delete(String id, int num) {
		int result = 0;
		result = bm.delete(num);
		if(result==1) {
			return GetMessage.getMessage("���䰡 �����Ǿ����ϴ�.", "/am/reviewList?id="+id);
		}
		return GetMessage.getMessage("���� ������ �����߽��ϴ�.", "/am/myReview?num="+num);
	}
}
