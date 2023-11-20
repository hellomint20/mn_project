package com.care.am.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.care.am.dto.reviewDTO;

public interface reviewMapper {
	//page(boardList)
	public int reviewAll();
	public List<reviewDTO> reviewSel(@Param("start") int start, @Param("end") int end);
	
	//page(reviewList)
	public int myReviewAll(String id);
	public List<reviewDTO> myReviewList(@Param("cId") String id, @Param("start") int start, @Param("end") int end);

	public reviewDTO myReview(int num);
	public int fixedForm(reviewDTO dto);
}
