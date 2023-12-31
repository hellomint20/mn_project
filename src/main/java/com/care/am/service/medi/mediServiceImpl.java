package com.care.am.service.medi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.care.am.common.GetMessage;
import com.care.am.dto.mediDTO;
import com.care.am.mapper.mediMapper;

@Service
public class mediServiceImpl implements mediService{

	@Autowired mediMapper mm;
	@Autowired mediFileService mfs;
	
	BCryptPasswordEncoder encoder; // 암호화
	public mediServiceImpl() {
		encoder = new BCryptPasswordEncoder();
	}
	
	// 병원 회원가입
	public String mediRegister(mediDTO dto, String[] addr) { 
		String ad ="";
		for(String a:addr) {
			ad += a+"/";
		}
		dto.setmAddr(ad); //합쳐진 주소 dto에 넣어줘
		dto.setmPw(encoder.encode(dto.getmPw())); //비밀번호 암호화해서 저장
		
		int result =0;
		try {
			result = mm.mediRegister(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result==1) {
			return GetMessage.getMessage("회원가입 성공!\\n영업 시간은 09:00 - 18:00 \\n점심 시간은 12:00 - 13:00 로 자동 설정되어있습니다\\n변경 필요 시 마이페이지에서 수정해주세요!", "/am/mediLogin");
		}
		else {
			return GetMessage.getMessage("회원가입 실패", "/am/mediRegister");
		}
	}
	
	// 병원 아이디확인
	public boolean mediIdCheck(String id) { 
		return mm.mediIdCheck(id);
	}
	// 병원 로그인확인
	public int logChk(String id, String pw) { 
	      mediDTO dto = mm.getMedi(id);
	      int result =1;
	      if(dto != null) {
	         
	         if(encoder.matches(pw, dto.getmPw()) || // 사용자가 입력한 평문이랑 암호화된 비밀번호 비교
	               pw.equals(dto.getmPw())) {
	            result =0;
	         }
	      }
	      return result;
	   }
	// 병원 자동로그인
	public void keepLogin(String mSession, String mId) { 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mSessionId", mSession);
		map.put("mId", mId);
		mm.keepLogin( map );
	}
	// 병원 세션
	public mediDTO getMediSessionId(String mSession) {
		return mm.getMediSession(mSession);
	}
	// 병원 아이디찾기
	public List<Map<String, String>> mediSearchId(String inputName, String inputTel) { 
		return mm.mediSearchId(inputName,inputTel);
	}
	// 병원 비밀번호 찾기	
	public mediDTO mediSearchPw(String inputId, String inputName, String inputTel) {
		mediDTO dto = mm.getMedi(inputId);
		if(dto!=null) {
			if(inputName.equals(dto.getmName())&& inputTel.equals(dto.getmTel())) {
				return dto;
			}
		}
		return null;
	}
	// 병원 새로운 비밀번호
	public String mediNewPwd(String newPw, String id) {
		mediDTO dto = mm.getMedi(id);
		dto.setmPw(encoder.encode(newPw));
		int result = mm.mediPwdChg(dto);
		if(result==1) {
			return GetMessage.getMessage("비밀번호 변경 성공", "/am/mediLogin");
		}
		else {
			return GetMessage.getMessage("비밀번호 변경 실패", "/am/mediSearchPw");
		}
	}
	
	// 병원 비밀번호 찾기
	public String mediPwdChk(String id, String pw) {
		mediDTO dto = mm.getMedi(id);
		
		if(dto != null) {
			try {
				if(encoder.matches(pw, dto.getmPw()) || pw.equals(dto.getmPw())) {
					String url = "/am/mediModify?id=" + id;
					return "<script> location.href='" + url + "';</script>";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return GetMessage.getMessage("비밀번호가 틀렸습니다.", "/am/mediPwdChk?id="+id);
	}
	
	// 병원 비밀번호 변경
	public String mediPwdChg(mediDTO dto,String pw, String newPw) {
		
		dto = mm.getMedi(dto.getmId());

		int result = 0;
		if (encoder.matches(pw, dto.getmPw()) || pw.equals(dto.getmPw())) {
			dto.setmPw(encoder.encode(newPw));
			result = mm.mediPwdChg(dto);
			if(result == 1) {
				return GetMessage.getMessage("비밀번호가 변경되었습니다", "/am/mediInfo?id=" +dto.getmId());
			}
		}
	return GetMessage.getMessage("비밀번호가 틀렸습니다", "/am/mediPwdChg?id=" + dto.getmId());
	}
	// 병원 정보가져오기
	public Map<String, Object> getMedi(String id){  
		mediDTO dto = mm.getMedi(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dto", dto);
		String[] addr = dto.getmAddr().split("/");
		if(addr.length>1) {
			map.put("addr1", addr[0]);
			map.put("addr2", addr[1]);
			map.put("addr3", addr[2]);
		}
		return map;
	}
	// 병원 정보 수정
	public String mediModify(mediDTO dto, MultipartFile file, String[] addr) {
		
		// 주소
		String ad ="";
		for(String a:addr) {
			ad += a+"/";
		}
		dto.setmAddr(ad); //합쳐진 주소 dto에 넣어줘
		
		//file 
		String dbImg = mm.getMedi(dto.getmId()).getmPhoto(); //디비에 저장되어있는 사진 이름
		if(!dbImg.equals("mediDefault.jpg")) {
			mfs.deleteImage(dbImg);
		}
		String originName = file.getOriginalFilename();	
		
		if(originName=="") { //파일 선택이 없다면
			dto.setmPhoto(dbImg); //디비에 있던 원래 파일 저장
			
		}else { // 파일 선택이 있다면
			dto.setmPhoto(mfs.saveFile(file)); //새로운 파일 저장
		}
		
		//text정보
		int result = mm.mediModify(dto);
		if(result==1) {
			mfs.deleteImage(originName);
			System.out.println("=-=-=-=-=-==-삭제할 파일이름: "+originName);
			return GetMessage.getMessage("정보가 수정되었습니다!", "/am/mediInfo?id=" + dto.getmId());
		}else {
			mfs.deleteImage(dto.getmPhoto());
			return GetMessage.getMessage( "정보 수정에 실패했습니다.", "/am/mediModify?id=" + dto.getmId());
		}
	}
	
	// 병원 회원 탈퇴
	public String mediDelete(mediDTO dto, String pw) {
		dto = mm.getMedi(dto.getmId());
		 int result =0 ;
			if (encoder.matches(pw, dto.getmPw()) || pw.equals(dto.getmPw())) {
				if(!dto.getmPhoto().equals("mediDefault.jpg")) {
			         mfs.deleteImage(dto.getmPhoto());
			      }
				result =  mm.mediDelete(dto);
				if(result == 1) {
					return GetMessage.getMessage("탈퇴가 완료되었습니다", "/am" );
				}
			}
		return GetMessage.getMessage("비밀번호가 틀렸습니다", "/am/mediPwdChk?id=" + dto.getmId());
	}
}
