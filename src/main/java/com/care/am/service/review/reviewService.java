package com.care.am.service.review;

import java.util.List;

import com.care.am.dto.reviewDTO;

public interface reviewService {
	public List<reviewDTO> boardList();
	public List<reviewDTO> reviewList(String id);
	public String fixedForm(reviewDTO dto, int num);
}
