package com.care.am.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pet")
public class petController {
	
	@GetMapping("petList") //Æê ¸®½ºÆ®
	public String petList() {
		return "am/pet/petList";
	}
	
	@GetMapping("petRegister") //Æê µî·Ï
	public String petRegister() {
		return "am/pet/petRegister";
	}
	
	@GetMapping("petModify") //Æê Á¤º¸ ¼öÁ¤
	public String petModify() {
		return "am/pet/petModify";
	}
	
	@PostMapping("petModify") //Æê Á¤º¸ ¼öÁ¤ Àû¿ë
	public void petModify(String id) {
		
	}
	
	@PostMapping("petDelete") //Æê Á¤º¸ »èÁ¦
	public void petDelete(String id) {
		
	}
}
