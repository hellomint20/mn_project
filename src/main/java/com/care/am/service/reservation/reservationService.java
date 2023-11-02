package com.care.am.service.reservation;

import java.util.List;
import java.util.Map;

import com.care.am.dto.pageDTO;

public interface reservationService {
	public List<Map<String, Object>> mediReservationList(String id, int page);
	public List<Map<String, String>> mediReservationWaitList(String id);
	public Map<String, String> reservationInfo(int rNum);
	
	//�ٵǸ� �����
	public List<Map<String, Object>> paging(int page, String id);
	public pageDTO pagingParam(int page, String id);//������
	
	public List<Map<String, Object>> waitList(String mId, int page); //���ο�����
	public pageDTO waitListPaging(int page, String mId); //���ο� ���� ����¡
	
	public List<Map<String, Object>> ACList(String mId, int page); //�������
	public pageDTO ACListPaging(int page, String mId); //������� ����¡
}
