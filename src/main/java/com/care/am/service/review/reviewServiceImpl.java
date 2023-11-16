package com.care.am.service.review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	/*
	public Map<String, Object> myReview(String id, int num) {
		
		reviewDTO dto = bm.myReview(id, num);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dto",dto);
		System.out.println(map.size());
		System.out.println(dto.getcId());
		System.out.println(dto.getmName());
		System.out.println(dto.getRvCont());
		System.out.println(dto.getRvTitle());
		
		return map;
	}*/
	
	public List<Map<String, Object>> myReview(String cId, int num){
		List<Map<String, Object>> map = new ArrayList<Map<String,Object>>();
		try {
			map = bm.myReview(cId, num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(map);
		System.out.println("1: "+map.get(0).get("r_date").getClass());
		System.out.println("2: "+map.get(0).get("r_date"));
		return map;
	}
	
	public String fixedForm(reviewDTO dto, int num) {
		int result =0;
		
		result = bm.fixedForm(dto);
		
		if(result == 1) {
			return GetMessage.getMessage("리뷰가 등록되었습니다!", "/am/fixedForm?num="+num); //나중에 경로 수정
		}
		return GetMessage.getMessage("리뷰 등록에 실패했습니다.", "/am/fixedForm");
	}
	

}
