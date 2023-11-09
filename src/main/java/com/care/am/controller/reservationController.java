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

//���� ���� ����(�մ� ����)
	@GetMapping("reservation") //���� ���� �⺻ ������
	public String reservation(Model model, reservationPagination pag
			, @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage)  {
		
		int mediCnt = rs.mediList().size();  // ��ü ���� ����
     
    	if (nowPage == null && cntPerPage == null) { 
    		nowPage = "1";
    		cntPerPage = "10";  // �� �������� ����Ǵ� �� ����
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
		int mediCnt = rs.mediSearch(mName); //�˻��� �̸� ����Ʈ ���� ��������
		
		if(nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "10"; //�� �������� ����Ǵ� ����
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
	@PostMapping("reservation/mediInfo") //���� ������ �˾�
	public Map<String, Object> mediInfo(@RequestBody String mediId){
		return rs.mediInfo(mediId);
	}

	@PostMapping("reservationForm/page") //���� ���� ������
	public String reservationFormPage(@RequestParam String mediId, Model model, HttpSession session ) {
		
		//���õ� ����
		model.addAttribute("mediInfo", rs.mediInfo(mediId));

		// �����ð� �������� 
		model.addAttribute("timeList", rs.mediTime(mediId));
		
		// �α����� ��� ���� ����Ʈ ��������
		model.addAttribute("p_list", rs.petList(session.getAttribute(LoginSession.cLOGIN).toString()));
		
		return "am/reservation/reservationForm";
	}

	@GetMapping("reservationPopup")//����Ϸ����˾�â
	public String reservationPopup(Model model, HttpSession session) {
		//�α����� ��� ����
		model.addAttribute("cId", session.getAttribute(LoginSession.cLOGIN));
		
		return "am/reservation/reservationPopup";
	}
	
	@ResponseBody
	@PostMapping("reservationRegister") //���� ���� DB ���
	public Map<String, String> reservationRegister(@RequestBody Map<String, Object> map, HttpSession session) {
		map.put("cId", session.getAttribute(LoginSession.cLOGIN).toString());
		
		Map<String, String> result = new HashMap<String, String>();
		
		result.put("result", Integer.toString(rs.reservationRegister(map)));
		result.put("userId", session.getAttribute(LoginSession.cLOGIN).toString());
		
		return result;
	}	
	
	@GetMapping("reservationList") //�մ� ���� ����Ʈ
	public String reservationList(@RequestParam String id, Model model, customerPagination pag
			, @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
				
		int customerCnt = rs.reservationList(id).size();  // ��ü ���� ����
			     
    	if (nowPage == null && cntPerPage == null) { 
    		nowPage = "1";
    		cntPerPage = "4";  // �� �������� ����Ǵ� �� ����
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
	
	//���� ������� ����(���� ���� - ���ο� ����)
	@GetMapping("reservationStateWait") //���� �������
	public String reservationState(@RequestParam String id, Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		
		model.addAttribute("wait", rs.waitList(id, page)); //���ο�����
		model.addAttribute("waitPaging", rs.waitListPaging(page, id));
		
		return "am/reservation/reservationStateWait";
	}
	
	//���� ������� ����(�������� - �������)
	@GetMapping("reservationStateAC")
	public String reservationStateAC(@RequestParam String id, Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		model.addAttribute("ac", rs.ACList(id, page)); //�������
		model.addAttribute("ACPaging", rs.ACListPaging(page, id));
		
		return "am/reservation/reservationStateAC";
	}
	
	@GetMapping("reservationCancel")  //�����������
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
	@PostMapping("reservationCount") //�ð��� ������ �� Ȯ��
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
