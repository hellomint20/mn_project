package com.care.am.service.review;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.am.common.GetMessage;
import com.care.am.dto.reviewDTO;
import com.care.am.mapper.reviewMapper;
import com.care.am.page.reviewPagination;

@Service
public class reviewServiceImpl implements reviewService{

	@Autowired reviewMapper bm;
	
	@Override	// 전체 글 수 조회
	public int reviewAll() {
		return bm.reviewAll();
	}
	
	@Override	// 페이징 후 글 조회
	public List<reviewDTO> reviewSel(reviewPagination rp) {
		int start = rp.getStart();
		int end = rp.getCntPerPage();
		System.out.println("serIMPL:" +rp.getCntPerPage());
		System.out.println("serIMPL:" + start + end);
		System.out.println("serIMPL:" +bm.reviewSel(start, end));
		return bm.reviewSel(start, end);
	}

	@Override	// 개인 글 수 조회
	public int myReviewAll(String id) {
		return bm.myReviewAll(id);
	}

	@Override	// 페이징 후 개인 글 조회
	public List<reviewDTO> myReviewList(reviewPagination rp, String id) {
		int start = rp.getStart();
		int end = rp.getCntPerPage();
		return bm.myReviewList(id, start, end);
	}

	@Override	// 후기 상세보기
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
	
	@Override	// 후기 작성
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
			return GetMessage.getMessage("리뷰가 등록되었습니다!", "/am/reviewList?id="+id);
		}
		return GetMessage.getMessage("리뷰 등록에 실패했습니다.", "/am/reservationList?id="+id);
	}
	
	@Override	// 후기 수정
	public String modiForm(reviewDTO dto) {
		int result = 0;
		result = bm.modiForm(dto);
		if(result ==1) {
			return GetMessage.getMessage("리뷰가 수정되었습니다.", "/am/myReview?num="+dto.getRvNo());
		}
		return GetMessage.getMessage("리뷰 수정에 실패했습니다.", "/am/modiForm?num="+dto.getRvNo());
	}
	
	@Override	// 후기 삭제
	public String delete(String id, int num) {
		int result = 0;
		result = bm.delete(num);
		if(result==1) {
			return GetMessage.getMessage("리뷰가 삭제되었습니다.", "/am/reviewList?id="+id);
		}
		return GetMessage.getMessage("리뷰 삭제에 실패했습니다.", "/am/myReview?num="+num);
	}
}
