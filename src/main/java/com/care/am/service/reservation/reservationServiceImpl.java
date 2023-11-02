package com.care.am.service.reservation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.am.dto.pageDTO;
import com.care.am.dto.reservationDTO;
import com.care.am.mapper.reservationMapper;

@Service
public class reservationServiceImpl implements reservationService {

	@Autowired
	reservationMapper rm;

	int pagingLimit = 5; // 페이지당 보여줄 글 개수
	int blockLimit = 5; // 하단에 보여줄 페이지 번호 개수

	public List<Map<String, Object>> mediReservationList(String mId, int page) {

		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();

		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("paging", paging(page, mId));
		pageMap.put("pagingParam", pagingParam(page, mId));

		listMap = rm.mediReservationList(mId);

		try {
			for (int i = 0; i < listMap.size(); i++) {
				Map<String, String> map = new HashMap<String, String>();

				listMap.get(i).put("year", listMap.get(i).get("r_date").toString().split("-")[0]);
				listMap.get(i).put("month", listMap.get(i).get("r_date").toString().split("-")[1]);
				listMap.get(i).put("day", listMap.get(i).get("r_date").toString().split("-")[2]);

				listMap.get(i).put("hour", listMap.get(i).get("r_time").toString().split("-")[0]);
				listMap.get(i).put("min", listMap.get(i).get("r_time").toString().split("-")[1]);
			}
			listMap.add(pageMap);
			System.out.println("ser listMap: " + listMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listMap;
	}

	public List<Map<String, Object>> waitList(String mId, int page) { // 새로운접수

		int pagingStart = (page - 1) * pagingLimit;

		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("start", pagingStart);
		pageMap.put("limit", pagingLimit);
		pageMap.put("mId", mId);

		List<Map<String, Object>> waitList = new ArrayList<Map<String, Object>>();
		waitList = rm.waitList(pageMap);
		
		try {
			for (int i = 0; i < waitList.size(); i++) {
				Map<String, String> map = new HashMap<String, String>();

				waitList.get(i).put("year", waitList.get(i).get("r_date").toString().split("-")[0]);
				waitList.get(i).put("month", waitList.get(i).get("r_date").toString().split("-")[1]);
				waitList.get(i).put("day", waitList.get(i).get("r_date").toString().split("-")[2]);

				waitList.get(i).put("hour", waitList.get(i).get("r_time").toString().split("-")[0]);
				waitList.get(i).put("min", waitList.get(i).get("r_time").toString().split("-")[1]);
			}
			//waitList.add(pageMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return waitList;

	}

	// 새로운접수 페이징
	public pageDTO waitListPaging(int page, String mId) {

		// 전체 글 갯수 조회
		int listCount = rm.waitListPaging(mId); // 13
		// System.out.println("ff: "+listCount);
		// System.out.println("11: "+rm.mediReservationListCount(mId));

		// 전체 페이지 갯수 계산(10/3=3.33333 => 4)
		int maxPage = (int) (Math.ceil((double) listCount / pagingLimit));
		// 시작 페이지 값 계산(1, 4, 7, 10, ~~~~)
		int startPage = (((int) (Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
		// 끝 페이지 값 계산(3, 6, 9, 12, ~~~~)
		int endPage = startPage + blockLimit - 1;
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		pageDTO pageDTO = new pageDTO();
		pageDTO.setPage(page);
		pageDTO.setMaxPage(maxPage);
		pageDTO.setStartPage(startPage);
		pageDTO.setEndPage(endPage);
		return pageDTO;
	}
	
	
	
	

	public List<Map<String, Object>> ACList(String mId, int page) { // 승인취소

		int pagingStart = (page - 1) * pagingLimit;

		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("start", pagingStart);
		pageMap.put("limit", pagingLimit);
		pageMap.put("mId", mId);

		List<Map<String, Object>> ACList = new ArrayList<Map<String, Object>>();
		ACList = rm.ACList(pageMap);
		
		try {
			for (int i = 0; i < ACList.size(); i++) {
				Map<String, String> map = new HashMap<String, String>();

				ACList.get(i).put("year", ACList.get(i).get("r_date").toString().split("-")[0]);
				ACList.get(i).put("month", ACList.get(i).get("r_date").toString().split("-")[1]);
				ACList.get(i).put("day", ACList.get(i).get("r_date").toString().split("-")[2]);

				ACList.get(i).put("hour", ACList.get(i).get("r_time").toString().split("-")[0]);
				ACList.get(i).put("min", ACList.get(i).get("r_time").toString().split("-")[1]);
			}
			//ACList.add(pageMap);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return ACList;

	}

	// 승인취소 페이징
	public pageDTO ACListPaging(int page, String mId) {

		// 전체 글 갯수 조회
		int listCount = rm.ACListPaging(mId); // 13
		// System.out.println("ff: "+listCount);
		// System.out.println("11: "+rm.mediReservationListCount(mId));

		// 전체 페이지 갯수 계산(10/3=3.33333 => 4)
		int maxPage = (int) (Math.ceil((double) listCount / pagingLimit));
		// 시작 페이지 값 계산(1, 4, 7, 10, ~~~~)
		int startPage = (((int) (Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
		// 끝 페이지 값 계산(3, 6, 9, 12, ~~~~)
		int endPage = startPage + blockLimit - 1;
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		pageDTO pageDTO = new pageDTO();
		pageDTO.setPage(page);
		pageDTO.setMaxPage(maxPage);
		pageDTO.setStartPage(startPage);
		pageDTO.setEndPage(endPage);
		return pageDTO;
	}

	public List<Map<String, String>> mediReservationWaitList(String mId) {

		List<Map<String, String>> waitList = new ArrayList<Map<String, String>>();
		waitList = rm.mediReservationWaitList(mId);

		try {
			for (int i = 0; i < waitList.size(); i++) {
				Map<String, String> map = new HashMap<String, String>();

				waitList.get(i).put("year", waitList.get(i).get("r_date").split("-")[0]);
				waitList.get(i).put("month", waitList.get(i).get("r_date").split("-")[1]);
				waitList.get(i).put("day", waitList.get(i).get("r_date").split("-")[2]);

				waitList.get(i).put("hour", waitList.get(i).get("r_time").split("-")[0]);
				waitList.get(i).put("min", waitList.get(i).get("r_time").split("-")[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return waitList;
	}

	public Map<String, String> reservationInfo(int rNum) {
		Map<String, String> info = new HashMap<String, String>();
		info = rm.reservationInfo(rNum);

		info.put("hour", info.get("r_time").split("-")[0]);
		info.put("min", info.get("r_time").split("-")[1]);

		info.put("year", info.get("r_date").split("-")[0]);
		info.put("month", info.get("r_date").split("-")[1]);
		info.put("day", info.get("r_date").split("-")[2]);

		return info;
	}

	public List<Map<String, Object>> paging(int page, String mId) {
		return null;
	}
	
	public pageDTO pagingParam(int page, String mId) {
		return null;
	}
}
