package com.care.am.service.review;

import java.util.List;

import com.care.am.dto.reviewDTO;
import com.care.am.page.reviewPagination;

public interface reviewService {
	//page(boardList)
	public int reviewAll();
	public List<reviewDTO> reviewSel(reviewPagination rp);

	//page(reviewList)
	public int myReviewAll(String id);
	public List<reviewDTO> myReviewList(reviewPagination rp, String id);

	public reviewDTO myReview(int num);
	public String fixedForm(reviewDTO dto, String id, int num);
	public String modiForm(reviewDTO dto);
	public String delete(String id, int num);
	
}
