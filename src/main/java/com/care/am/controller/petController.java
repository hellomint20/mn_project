package com.care.am.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.am.dto.petDTO;
import com.care.am.dto.typeDTO;
import com.care.am.service.pet.petService;

@Controller
@RequestMapping("pet")
public class petController {
	
	@Autowired petService ps;
	
	@GetMapping("petList") //�� ����Ʈ
	public String petList(@RequestParam String id, Model model) {
		List<petDTO> list = ps.petList(id);
		model.addAttribute("list", list);
		return "am/pet/petList";
	}
	
	@GetMapping("petRegister") //�� ��� ������
	public String petRegister(Model model) {
		List<typeDTO> list = ps.petType();
		model.addAttribute("list", list);
		return "am/pet/petRegister";
	}
	
	@PostMapping("petRegister") //�� ���
	public String petRegister(petDTO dto, HttpServletResponse res) throws Exception {
		System.out.println("pR:" + dto.getcId());
		System.out.println("pR:" + dto.getpAge());
		System.out.println("pR:" + dto.getpName());
		System.out.println("pR:" + dto.getpSection());
		System.out.println("pR:" + dto.getpSex());
		System.out.println("pR:" + dto.getpType());
		
		String msg = ps.petRegister(dto);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print( msg );
		
		return "am/pet/petRegister";
	}
	
	@GetMapping("petModify") //�� ���� ����
	public String petModify(@RequestParam int num, Model model) {
		System.out.println(num);
		petDTO dto = ps.petInfo(num);
		model.addAttribute("dto", dto);
		return "am/pet/petModify";
	}
	
	@PostMapping("petModify") //�� ���� ���� ����
	public void petModify(String id) {
		
	}
	
	@PostMapping("petDelete") //�� ���� ����
	public void petDelete(String id) {
		
	}
}
