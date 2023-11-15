package com.care.am.mapper;

import java.util.List;

import com.care.am.dto.reviewDTO;

public interface reviewMapper {
	public List<reviewDTO> boardList();
	public List<reviewDTO> reviewList(String id);
	public int fixedForm(reviewDTO dto);
	
}
