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
		dto.setmAddr(ad); //������ �ּ� dto�� �־���
		dto.setmPw(encoder.encode(dto.getmPw())); //��й�ȣ ��ȣȭ�ؼ� ����
		
		int result =0;
		try {
			result = mm.mediRegister(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result==1) {
			return GetMessage.getMessage("ȸ������ ����!\\n���� �ð��� 09:00 - 18:00 \\n���� �ð��� 12:00 - 13:00 �� �ڵ� �����Ǿ��ֽ��ϴ�\\n���� �ʿ� �� �������������� �������ּ���!", "/am/mediLogin");
		}
		else {
			return GetMessage.getMessage("ȸ������ ����", "/am/mediRegister");
		}
	}
	public boolean mediIdCheck(String id) {
		return mm.mediIdCheck(id);
	}
	public int logChk(String id, String pw) {
	      mediDTO dto = mm.getMedi(id);
	      int result =1;
	      if(dto != null) {
	         
	         if(encoder.matches(pw, dto.getmPw()) || // ����ڰ� �Է��� ���̶� ��ȣȭ�� ��й�ȣ ��
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
			return GetMessage.getMessage("��й�ȣ ���� ����", "/am/mediLogin");
		}
		else {
			return GetMessage.getMessage("��й�ȣ ���� ����", "/am/mediSearchPw");
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
		return GetMessage.getMessage("��й�ȣ�� Ʋ�Ƚ��ϴ�.", "/am/mediPwdChk?id="+id);
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
				return GetMessage.getMessage("��й�ȣ�� ����Ǿ����ϴ�", "/am/mediInfo?id=" +dto.getmId());
			}
		}
	return GetMessage.getMessage("��й�ȣ�� Ʋ�Ƚ��ϴ�", "/am/mediPwdChg?id=" + dto.getmId());
	}
	
	public String mediModify(mediDTO dto, MultipartFile file, String[] addr) {
		
		// �ּ�
		String ad ="";
		for(String a:addr) {
			ad += a+"/";
		}
		dto.setmAddr(ad); //������ �ּ� dto�� �־���
		
		//file 
		String dbImg = mm.getMedi(dto.getmId()).getmPhoto(); //��� ����Ǿ��ִ� ���� �̸�
		mfs.deleteImage(dbImg);
		String originName = file.getOriginalFilename();	
		
		if(originName=="") { //���� ������ ���ٸ�
			dto.setmPhoto(dbImg); //��� �ִ� ���� ���� ����
			
		}else { // ���� ������ �ִٸ�
			dto.setmPhoto(mfs.saveFile(file)); //���ο� ���� ����
		}
		
		//text����
		int result = mm.mediModify(dto);
		String msg ="", url="";
		if(result==1) {
			mfs.deleteImage(originName);
			return GetMessage.getMessage("������ �����Ǿ����ϴ�!", "/am/mediInfo?id=" + dto.getmId());
		}else {
			mfs.deleteImage(dto.getmPhoto());
			return GetMessage.getMessage( "���� ������ �����߽��ϴ�.", "/am/mediModify?id=" + dto.getmId());
		}
	}
	public String mediDelete(mediDTO dto, String pw) {
		dto = mm.getMedi(dto.getmId());
		 int result =0 ;
			if (encoder.matches(pw, dto.getmPw()) || pw.equals(dto.getmPw())) {
				result =  mm.mediDelete(dto);
				if(result == 1) {
					return GetMessage.getMessage("Ż�� �Ϸ�Ǿ����ϴ�", "/am" );
				}
			}
		return GetMessage.getMessage("��й�ȣ�� Ʋ�Ƚ��ϴ�", "/am/mediPwdChk?id=" + dto.getmId());
	}
		
}
