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
	
	@Override	// ��ü �� �� ��ȸ
	public int reviewAll() {
		return bm.reviewAll();
	}
	
	@Override	// ����¡ �� �� ��ȸ
	public List<reviewDTO> reviewSel(reviewPagination rp) {
		int start = rp.getStart();
		int end = rp.getCntPerPage();
		System.out.println("serIMPL:" +rp.getCntPerPage());
		System.out.println("serIMPL:" + start + end);
		System.out.println("serIMPL:" +bm.reviewSel(start, end));
		return bm.reviewSel(start, end);
	}

	@Override	// ���� �� �� ��ȸ
	public int myReviewAll(String id) {
		return bm.myReviewAll(id);
	}

	@Override	// ����¡ �� ���� �� ��ȸ
	public List<reviewDTO> myReviewList(reviewPagination rp, String id) {
		int start = rp.getStart();
		int end = rp.getCntPerPage();
		return bm.myReviewList(id, start, end);
	}

	@Override	// �ı� �󼼺���
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
	
	@Override	// �ı� �ۼ�
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
	
	@Override	// �ı� ����
	public String modiForm(reviewDTO dto) {
		int result = 0;
		result = bm.modiForm(dto);
		if(result ==1) {
			return GetMessage.getMessage("���䰡 �����Ǿ����ϴ�.", "/am/myReview?num="+dto.getRvNo());
		}
		return GetMessage.getMessage("���� ������ �����߽��ϴ�.", "/am/modiForm?num="+dto.getRvNo());
	}
	
	@Override	// �ı� ����
	public String delete(String id, int num) {
		int result = 0;
		result = bm.delete(num);
		if(result==1) {
			return GetMessage.getMessage("���䰡 �����Ǿ����ϴ�.", "/am/reviewList?id="+id);
		}
		return GetMessage.getMessage("���� ������ �����߽��ϴ�.", "/am/myReview?num="+num);
	}
}
