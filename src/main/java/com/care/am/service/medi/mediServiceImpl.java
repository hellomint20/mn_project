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
		dto.setmPw((encoder.encode(dto.getmPw()))); //비밀번호 암호화해서 저장
		
		int result =0;
		try {
			result = mm.mediRegister(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result==1) {
			return GetMessage.getMessage("회원가입 성공", "/am/mediLogin");
		}
		else {
			return GetMessage.getMessage("회원가입 실패", "/am/mediRegister");
		}
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
	
	public String mediModify(mediDTO dto, MultipartFile image_file_name, String[] addr) {
		String ad ="";
		for(String a:addr) {
			ad += a+"/";
		}
		dto.setmAddr(ad); //합쳐진 주소 dto에 넣어줘
		//---------------------------------/*
		/*if(image_file_name.isEmpty()) { //파일이 없다면
			dto.setImageFileName("nan");
		}else { //파일이 존재하는 경우
			dto.setImageFileName(bfs.saveFile(image_file_name) ); //특정위치에 파일 저장 + 파일 이름 만들기
		}*/
		if(image_file_name.isEmpty()) {
			dto.setmPhoto("default.jpg");
		}else {
			dto.setmPhoto(mfs.saveFile(image_file_name));
		}
		
		
		String originName = null;
		System.out.println("mPhotot: "+dto.getmPhoto()); //null
		
		if(!image_file_name.isEmpty()) { //수정됨
			originName = dto.getmPhoto();
			dto.setmPhoto(mfs.saveFile(image_file_name));
		}
		System.out.println("originName: "+originName);
		
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

}
