package com.care.am.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.care.am.dto.reviewDTO;

public interface reviewMapper {
	public List<reviewDTO> boardList();
	public List<reviewDTO> reviewList(String id);
	public reviewDTO myReview(int num);
	public int fixedForm(reviewDTO dto);
	public int fixResult(int num);
	public int modiForm(reviewDTO dto);
	public int delete(int num);
	
}
