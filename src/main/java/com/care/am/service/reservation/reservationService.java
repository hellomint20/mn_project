package com.care.am.service.reservation;

import java.util.List;
import java.util.Map;
import com.care.am.dto.mediDTO;
import com.care.am.dto.petDTO;

public interface reservationService {
	public List<Map<String, mediDTO>> mediList(); 
	public Map<String, Object> mediInfo(String mediName);
	public List<Map<String , petDTO>> petList();
}
