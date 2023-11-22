package com.care.am.service.medi;

import java.util.HashMap;
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
	
	BCryptPasswordEncoder encoder;
	public mediServiceImpl() {
		encoder = new BCryptPasswordEncoder();
	}
	
	
	public String register(mediDTO dto) {
		return null;
	}
	
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
	public boolean mediIdCheck(String id) {
		return mm.mediIdCheck(id);
	}
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
	
	public void keepLogin(String mSession, String mId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mSessionId", mSession);
		map.put("mId", mId);
		mm.keepLogin( map );
	}
	
	public mediDTO getMediSessionId(String mSession) {
		return mm.getMediSession(mSession);
	}
	
	public String mediSearchId(String inputName, String inputTel) {
		String result ="";
		mediDTO dto = mm.mediSearchId(inputName,inputTel);
		if(dto!=null) {
				result=dto.getmId();
		}
		return result;
	}
		
	public mediDTO mediSearchPw(String inputId, String inputName, String inputTel) {
		mediDTO dto = mm.getMedi(inputId);
		if(dto!=null) {
			if(inputName.equals(dto.getmName())&& inputTel.equals(dto.getmTel())) {
				return dto;
			}
		}
		return null;
	}
	
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
	
	public String mediPwdChg(mediDTO dto,String pw, String newPw) {
		
		dto = mm.getMedi(dto.getmId());
		
		System.out.println("pw: "+pw);
		System.out.println("getpw : "+dto.getmPw());
		
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
	
	public String mediModify(mediDTO dto, MultipartFile file, String[] addr) {
		
		// 주소
		String ad ="";
		for(String a:addr) {
			ad += a+"/";
		}
		dto.setmAddr(ad); //합쳐진 주소 dto에 넣어줘
		
		//file 
		String dbImg = mm.getMedi(dto.getmId()).getmPhoto(); //디비에 저장되어있는 사진 이름
		mfs.deleteImage(dbImg);
		String originName = file.getOriginalFilename();	
		
		if(originName=="") { //파일 선택이 없다면
			dto.setmPhoto(dbImg); //디비에 있던 원래 파일 저장
			
		}else { // 파일 선택이 있다면
			dto.setmPhoto(mfs.saveFile(file)); //새로운 파일 저장
		}
		
		//text정보
		int result = mm.mediModify(dto);
		String msg ="", url="";
		if(result==1) {
			mfs.deleteImage(originName);
			return GetMessage.getMessage("정보가 수정되었습니다!", "/am/mediInfo?id=" + dto.getmId());
		}else {
			mfs.deleteImage(dto.getmPhoto());
			return GetMessage.getMessage( "정보 수정에 실패했습니다.", "/am/mediModify?id=" + dto.getmId());
		}
	}
	public String mediDelete(mediDTO dto, String pw) {
		dto = mm.getMedi(dto.getmId());
		 int result =0 ;
			if (encoder.matches(pw, dto.getmPw()) || pw.equals(dto.getmPw())) {
				result =  mm.mediDelete(dto);
				if(result == 1) {
					return GetMessage.getMessage("탈퇴가 완료되었습니다", "/am" );
				}
			}
		return GetMessage.getMessage("비밀번호가 틀렸습니다", "/am/mediPwdChk?id=" + dto.getmId());
	}
		
}
