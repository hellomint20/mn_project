package com.care.am.service.review;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.care.am.dto.pageDTO;
import com.care.am.dto.reviewDTO;

public interface reviewService {
	public List<reviewDTO> boardList();
	
	public reviewDTO myReview(int num);
	public String fixedForm(reviewDTO dto, String id, int num);
	public String modiForm(reviewDTO dto);
	public String delete(String id, int num);
	
	public List<reviewDTO> reviewList(String id);
}