package com.care.am.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
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

	@Autowired 
	private reservationService rs;

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
    	model.addAttribute("search", 0);
    	
		return "am/reservation/reservationPage";
	}

	@GetMapping("reservationSearch")
	public String reservationSearch(@RequestParam String mName, Model model, reservationPagination pag,
									@RequestParam(value = "nowPage", required = false)String nowPage,
									@RequestParam(value = "cntPerPage", required = false)String cntPerPage) {
		int mediCnt = rs.mediSearch(mName); //검색한 이름 리스트 갯수 가져오기
		
		if(nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "10"; //한 페이지에 노출되는 갯수
		}else if(nowPage == null) {
			nowPage = "1";
		}else if(cntPerPage == null) {
			cntPerPage = "10";
		}
		pag = new reservationPagination(mediCnt, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
    	model.addAttribute("paging", pag);
    	model.addAttribute("viewAll", rs.mediSelectSearch(mName, pag));
    	model.addAttribute("search", 1);
    	model.addAttribute("mName", mName);
		
		return "am/reservation/reservationPage";
	}
	
	@ResponseBody
	@PostMapping("reservation/mediInfo") //병원 상세정보 팝업
	public Map<String, Object> mediInfo(@RequestBody String mediId){
		return rs.mediInfo(mediId);
	}

	@PostMapping("reservationForm/page") //병원 예약 페이지
	public String reservationFormPage(@RequestParam String mediId, Model model, HttpSession session ) {
		
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
		model.addAttribute("cId", session.getAttribute(LoginSession.cLOGIN));
		
		return "am/reservation/reservationPopup";
	}
	
	@ResponseBody
	@PostMapping("reservationRegister") //병원 예약 DB 등록
	public Map<String, String> reservationRegister(@RequestBody Map<String, Object> map, HttpSession session) {
		map.put("cId", session.getAttribute(LoginSession.cLOGIN).toString());
		
		Map<String, String> result = new HashMap<String, String>();
		
		result.put("result", Integer.toString(rs.reservationRegister(map)));
		result.put("userId", session.getAttribute(LoginSession.cLOGIN).toString());
		
		return result;
	}	
	
	@GetMapping("reservationList") //손님 예약 리스트
	public String reservationList(@RequestParam String id, Model model, customerPagination pag
			, @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
				
		int customerCnt = rs.reservationList(id).size();  // 전체 병원 갯수
			     
    	if (nowPage == null && cntPerPage == null) { 
    		nowPage = "1";
    		cntPerPage = "4";  // 한 페이지에 노출되는 글 갯수
    	} else if (nowPage == null) {
    		nowPage = "1";
    	} else if (cntPerPage == null) { 
    		cntPerPage = "4";
    	}
    	
    	pag = new customerPagination(customerCnt, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
    	
    	model.addAttribute("paging", pag);
    	model.addAttribute("viewAll", rs.customerResList(id, pag));
		
		return "am/reservation/reservationList";
	}
	
	//병원 예약상태 관련(병원 기준 - 새로운 접수)
	@GetMapping("reservationStateWait") //병원 예약상태
	public String reservationState(@RequestParam String id, Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		
		model.addAttribute("wait", rs.waitList(id, page)); //새로운접수
		model.addAttribute("waitPaging", rs.waitListPaging(page, id));
		
		return "am/reservation/reservationStateWait";
	}
	
	//병원 예약상태 관련(병원기준 - 승인취소)
	@GetMapping("reservationStateAC")
	public String reservationStateAC(@RequestParam String id, Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		model.addAttribute("ac", rs.ACList(id, page)); //승인취소
		model.addAttribute("ACPaging", rs.ACListPaging(page, id));
		
		return "am/reservation/reservationStateAC";
	}
	
	@GetMapping("reservationCancel")  //병원예약취소
	public void reservationCancel(@RequestParam String id, @RequestParam int num,
								HttpServletResponse res) throws Exception {
		String msg = rs.reserCancel(id, num);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(msg);
	}
	
	@GetMapping("reserState1") 
	public String reserState1(@RequestParam int num, @RequestParam String email, @RequestParam String mId) {
		int result = rs.reserState(num, 1);
		if(result == 1) {
			String toMail = email;
			return "redirect:/reserState1/"+toMail+"/"+mId+"/";
		}
		return "redirect:/reservationState";
	}
	
	@PostMapping("reserState2")
    public String reserState2(@RequestParam String email,@RequestParam int num,
        @RequestParam String mId, @RequestParam String cont) {
		int result = rs.reserState(num, 0);
		if(result == 1) {
			String toMail = email;
			return "redirect:/reserState2/"+toMail+"/"+cont+"/"+mId+"/";
		}
		return "redirect:/reservationState";
	}

	@GetMapping("reservationApplyPopup") 
	public String reservationApplyPopup(@RequestParam int rNum, Model model) {
		System.out.println(rNum);
		model.addAttribute("info", rs.reservationInfo(rNum));
		model.addAttribute("num", rNum);
		return "am/reservation/reservationApplyPopup";
	}
	
	@ResponseBody
	@PostMapping("reservationCount") //시간별 예약자 수 확인
	public Map<String, String> reservationCount(@RequestBody Map<String, Object> map) {
		return rs.reservationCount(map);
	}
	
	@ResponseBody
	@PostMapping("reservationCheck")
	public String reservationCheck(HttpSession session, @RequestBody Map<String, String> map ) {
		String size = "";
		
		if(rs.reservationCheck(map) != null) {
			size = "1";
		} 
		
		map.put("cId", session.getAttribute(LoginSession.cLOGIN).toString());
		
		return size;
	}
}
