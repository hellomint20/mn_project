package com.care.am.controller;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.care.am.common.LoginSession;
import com.care.am.page.customerPagination;
import com.care.am.page.reservationPagination;
import com.care.am.service.reservation.reservationService;


@Controller
public class reservationController {

	@Autowired reservationService rs;
	
	
	//병원 예약 관련(손님 기준)
	@GetMapping("reservation") //병원 예약 기본 페이지
	public String reservation(Model model, reservationPagination pag
			, @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage)  {
		
		int mediCnt = rs.mediList().size();  // 전체 병원 갯수
     
    	if (nowPage == null && cntPerPage == null) { 
    		nowPage = "1";
    		cntPerPage = "10";  // 한 페이지에 노출되는 글 갯수
    	} else if (nowPage == null) {
    		nowPage = "1";
    	} else if (cntPerPage == null) { 
    		cntPerPage = "10";
    	}

    	
    	pag = new reservationPagination(mediCnt, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
    	model.addAttribute("paging", pag);
    	model.addAttribute("viewAll", rs.mediSelectList(pag));
    	
		return "am/reservation/reservationPage";
	}
	
	@ResponseBody
	@PostMapping("reservation/mediInfo") //병원 상세정보 팝업
	public Map<String, Object> mediInfo(@RequestBody String mediId){
		return rs.mediInfo(mediId);
	}

	@PostMapping("reservationForm/page") //병원 예약 페이지
	public String reservationFormPage(@RequestParam String mediId, Model model, HttpSession session) {

		//선택된 병원
		model.addAttribute("mediInfo", rs.mediInfo(mediId));

		// 영업시간 가져오기 
		model.addAttribute("timeList", rs.mediTime(mediId));
		
		// 로그인한 사람 동물 리스트 가져오기
		model.addAttribute("p_list", rs.petList(session.getAttribute(LoginSession.cLOGIN).toString()));
		
		return "am/reservation/reservationForm";
	}

	@GetMapping("reservationPopup")//예약완료후팝업창
	public String reservationPopup(Model model, HttpSession session) {
		//로그인한 사람 정보
		model.addAttribute("cId", session.getAttribute(LoginSession.cLOGIN).toString());
		
		return "am/reservation/reservationPopup";
	}
	
	@ResponseBody
	@PostMapping("reservationRegister") //병원 예약 DB 등록
	public String reservationRegister(@RequestBody Map<String, Object> map, HttpSession session) {
		map.put("cId", session.getAttribute(LoginSession.cLOGIN).toString());
		int result = rs.reservationRegister(map);
		
		return Integer.toString(result);
	}
	

	@GetMapping("reservationList") //손님 예약 리스트
	public String reservationList(@RequestParam String id, Model model, customerPagination pag
			, @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		
		model.addAttribute("list",rs.reservationList(id));
		
		int customerCnt = rs.reservationList(id).size();  // 전체 병원 갯수
		
		System.out.println(customerCnt);
	     
    	if (nowPage == null && cntPerPage == null) { 
    		nowPage = "1";
    		cntPerPage = "4";  // 한 페이지에 노출되는 글 갯수
    	} else if (nowPage == null) {
    		nowPage = "1";
    	} else if (cntPerPage == null) { 
    		cntPerPage = "4";
    	}
    	
    	pag = new customerPagination(customerCnt, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
    	
    	System.out.println("pag" + pag);
    	model.addAttribute("paging", pag);
    	model.addAttribute("viewAll", rs.customerResList(id, pag));
		
		return "am/reservation/reservationList";
	}
	
	//병원 예약상태 관련(병원 기준)
	@GetMapping("reservationState") //병원 예약상태
	public String reservationState() {
		return "am/reservation/reservationState";
	}
	
	@PostMapping("reservationState") //병원 예약 승인, 취소 (map으로 묶어서 받기)
	public void reservationState(String id) {
		
	}

	@GetMapping("reservationApplyPopup") 
	public String reservationApplyPopup(String id) {
		return "am/reservation/reservationApplyPopup";
	}

	@ResponseBody
	@PostMapping("reservationCount") //시간별 예약자 수 확인
	public Map<String, String> reservationCount(@RequestBody Map<String, Object> map) {
		return rs.reservationCount(map);
	}

	
}
