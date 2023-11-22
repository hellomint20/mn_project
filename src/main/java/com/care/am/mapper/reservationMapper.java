package com.care.am.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.care.am.dto.mediDTO;
import com.care.am.dto.petDTO;
import com.care.am.dto.reservationDTO;
import com.care.am.page.reservationPagination;

@Mapper
public interface reservationMapper {

	public List<Map<String, String>> reservationList(String cId);
	public List<Map<String, mediDTO>> mediList(); //병원 리스트
	public int mediSearch(String mName); //검색한 이름 리스트 갯수 가져오기
	public List<Map<String, String>> mediSelectSearch(@Param("mName") String mName, @Param("start") String start, @Param("end") String end); //pag 해당하는 만큼 검색 리스트 가져오기
	public Map<String, Object> mediInfo(String mediId); //병원 상세정보
	public Map<String, Object> mediTime(String mediId); //병원 Time
	public List<Map<String, petDTO>> petList(String id); //사용자 pet list
	public List<Map<String, petDTO>> petList(); //사용자 pet list
	public List<Map<String, Object>> reservationCount(Map<String, Object> map); ////시간별 예약자 수 확인
	public Map<String, Object> peopleCount(Map<String, Object> map);
	public List<Map<String, String>> mediSelectList(reservationPagination pag);
	public List<Map<String, String>> customerResList(@Param("id") String id, @Param("start") String start, @Param("end") String end);
	public Map<String, String> reservationCheck(Map<String, String> map);
	public int reserCancel(int num);
	public int reserState(@Param("apply") String apply, @Param("num") int num);
	public List<Map<String , String>> mediReservationList(String mId);
	public List<Map<String, String>> mediReservationWaitList(String mId);
	public Map<String, String> reservationInfo(int rNum); //병원 팝업 예약 정보
	public List<Map<String, Object>> waitList(Map<String, Object> pageMap); //병원 새로운 접수 리스트
	public Integer waitListPaging(String mId); //병원 새로운 접수 페이징
	public Map<String, String> getResInfo(int num);
	public List<Map<String, Object>> AList(Map<String, Object> pageMap); //병원 승인 리스트
	public Integer AListPaging(String mId); // 병원 승인 페이징
	public List<Map<String, Object>> CList(Map<String, Object> pageMap); //병원 취소 리스트
	public Integer CListPaging(String mId); // 병원 취소 페이징
	public int fix(@Param("mId") String mId,  @Param("rFix") int r_fix, @Param("Rnum") int Rnum); //진료여부 확정
}

