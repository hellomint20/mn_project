package com.care.am.service.review;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.care.am.dto.reviewDTO;

public interface reviewService {
	public List<reviewDTO> boardList();
	public List<reviewDTO> reviewList(String id);
	
	public List<Map<String, Object>> myReview(String cId, int num);
	
	public String fixedForm(reviewDTO dto, int num);
}
