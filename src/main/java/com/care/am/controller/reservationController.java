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
    	
		return "am/reservation/reservationPage";
	}
	
	@ResponseBody
	@PostMapping("reservation/mediInfo") //���� ������ �˾�
	public Map<String, Object> mediInfo(@RequestBody String mediId){
		return rs.mediInfo(mediId);
	}

	@PostMapping("reservationForm/page") //���� ���� ������
	public String reservationFormPage(@RequestParam String mediId, Model model, HttpSession session) {

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
		model.addAttribute("cId", session.getAttribute(LoginSession.cLOGIN).toString());
		
		return "am/reservation/reservationPopup";
	}
	
	@ResponseBody
	@PostMapping("reservationRegister") //���� ���� DB ���
	public String reservationRegister(@RequestBody Map<String, Object> map, HttpSession session) {
		map.put("cId", session.getAttribute(LoginSession.cLOGIN).toString());
		int result = rs.reservationRegister(map);
		
		return Integer.toString(result);
	}
	

	@GetMapping("reservationList") //�մ� ���� ����Ʈ
	public String reservationList(@RequestParam String id, Model model, customerPagination pag
			, @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		
		model.addAttribute("list",rs.reservationList(id));
		
		int customerCnt = rs.reservationList(id).size();  // ��ü ���� ����
		
		System.out.println(customerCnt);
	     
    	if (nowPage == null && cntPerPage == null) { 
    		nowPage = "1";
    		cntPerPage = "4";  // �� �������� ����Ǵ� �� ����
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
	
	//���� ������� ����(���� ����)
	@GetMapping("reservationState") //���� �������
	public String reservationState() {
		return "am/reservation/reservationState";
	}
	
	@PostMapping("reservationState") //���� ���� ����, ��� (map���� ��� �ޱ�)
	public void reservationState(String id) {
		
	}

	@GetMapping("reservationApplyPopup") 
	public String reservationApplyPopup(String id) {
		return "am/reservation/reservationApplyPopup";
	}

	@ResponseBody
	@PostMapping("reservationCount") //�ð��� ������ �� Ȯ��
	public Map<String, String> reservationCount(@RequestBody Map<String, Object> map) {
		return rs.reservationCount(map);
	}

	
}
