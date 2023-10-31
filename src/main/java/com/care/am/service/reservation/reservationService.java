package com.care.am.service.reservation;

import java.util.List;
import java.util.Map;

public interface reservationService {
	public List<Map<String, String>> mediReservationList(String id);
	//public Map<String, Object> paging(int num);
}
