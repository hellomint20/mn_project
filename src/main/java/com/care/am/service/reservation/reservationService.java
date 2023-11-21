package com.care.am.service.reservation;

import java.util.List;
import java.util.Map;
import com.care.am.dto.mediDTO;
import com.care.am.dto.petDTO;
import com.care.am.page.customerPagination;
import com.care.am.page.reservationPagination;
import com.care.am.dto.pageDTO;

public interface reservationService {
	public List<Map<String, mediDTO>> mediList(); //���� ����Ʈ
	public Map<String, Object> mediInfo(String mediName); //���� ������
	public List<String> mediTime(String name); //���� Time
	public List<Map<String , petDTO>> petList(String id); //����� pet lists
	public Map<String, String> reservationCount(Map<String, Object> map); ////�ð��� ������ �� Ȯ��
	public List<Map<String, String>> reservationList(String id);
	public List<Map<String, String>> mediSelectList(reservationPagination pag);
	public List<Map<String, String>> customerResList( String id, customerPagination pag);
	public int mediSearch(String mName); //�˻��� �̸� ����Ʈ ���� ��������
	public List<Map<String, String>> mediSelectSearch(String mName, reservationPagination pag); //pag �ش��ϴ� ��ŭ �˻� ����Ʈ ��������
	public Map<String, String> reservationCheck(Map<String, String> map);
	public Map<String, String> reservationInfo(int rNum); //���� �˾� ���� ����
	public List<Map<String, Object>> waitList(String mId, int page); //���� ���ο����� ����Ʈ
	public pageDTO waitListPaging(int page, String mId); //���� ���ο� ���� ����¡
	public List<Map<String, Object>> ACList(String mId, int page); //���� ������� ����Ʈ
	public pageDTO ACListPaging(int page, String mId); //���� ������� ����¡
	public int reserCancel(int num);
	public int reserState(int num, int state);

}
