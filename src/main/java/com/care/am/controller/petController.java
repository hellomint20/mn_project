package com.care.am.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.care.am.common.LoginSession;
import com.care.am.dto.petDTO;
import com.care.am.dto.typeDTO;
import com.care.am.service.pet.petService;

@Controller
@RequestMapping("pet")
public class petController implements LoginSession{
	
	@Autowired petService ps;
	
	@GetMapping("petList") //펫 리스트
	public String petList(@RequestParam String id, Model model) {
		List<petDTO> list = ps.petList(id);
		model.addAttribute("list", list);
		return "am/pet/petList";
	}
	
	@GetMapping("petRegister") //펫 등록 페이지
	public String petRegister() {
		return "am/pet/petRegister";
	}

	@PostMapping("petRegister") //펫 등록
	public void petRegister(@RequestParam(value="pType", required=false) String pType,
							 @RequestParam(value="writeType", required=false) String writeType,
							 @RequestParam String pSec,
							 @RequestParam(value="file",required = false)  MultipartFile file,
							 petDTO dto, HttpServletResponse res) throws Exception {
		System.out.println("2: "+pSec);
		System.out.println("2: "+pType);
		System.out.println("2: "+writeType);
		
		if(pSec.equals("3")) {
			dto.setpType(writeType);
		}else {
			dto.setpType(pType);
		}
		dto.setpSection(pSec);
		String msg = ps.petRegister(dto, file);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print( msg );
	}
	
	@GetMapping("petModify") //펫 정보 수정
	public String petModify(@RequestParam int num, Model model) {
		petDTO dto = ps.petInfo(num);
		model.addAttribute("dto", dto);
		return "am/pet/petModify";
	}
	
	@PostMapping("petModify") //펫 정보 수정 적용
	public void petModify(@RequestParam(value="pType", required=false) String pType,
							@RequestParam(value="writeType", required=false) String writeType,
							@RequestParam String pSec,
							@RequestParam(value="file",required = false)  MultipartFile file,
							petDTO dto, HttpServletResponse res) throws Exception {
		
		if(pSec.equals("3")) {
			dto.setpType(writeType);
		}else {
			dto.setpType(pType);
		}
		dto.setpSection(pSec);
		
		String msg = ps.petModify(dto, file);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print( msg );
	}
	
	@GetMapping("petDelete") //펫 정보 삭제
	public void petDelete(@RequestParam int num, @RequestParam String id,
						HttpServletResponse res) throws Exception {
		String msg = ps.petDel(num, id);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(msg);
	}
	
	
	@RequestMapping(value="/petType", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<typeDTO>> petType(String data) throws Exception{	// petType 리스트
		ResponseEntity<List<typeDTO>> entity =null;
		try{
			List<typeDTO> list= ps.petType(data);
			entity =new ResponseEntity<List<typeDTO>>(list, HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			//에러일 경우 에러 코드 전송 400
			entity = new ResponseEntity<List<typeDTO>>(HttpStatus.BAD_REQUEST);		
		}
		return entity;
	}
	
}

