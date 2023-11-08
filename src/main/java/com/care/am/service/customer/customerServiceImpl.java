package com.care.am.service.customer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.care.am.common.GetMessage;
import com.care.am.dto.customerDTO;
import com.care.am.mapper.customerMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class customerServiceImpl implements customerService {

	@Autowired
	customerMapper cm;

	BCryptPasswordEncoder encoder;

	public customerServiceImpl() {
		encoder = new BCryptPasswordEncoder();
	}

	public String register(customerDTO dto) {
		int result = 0;

		dto.setcPw(encoder.encode(dto.getcPw()));
		try {
			result = cm.register(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result == 1) {
			return GetMessage.getMessage("ȸ������ ����", "/am/customerLogin");
		}
		return GetMessage.getMessage("ȸ������ ����", "/am/customerRegister");
	}
	
	public String customerSearchId(String inputName, String inputEmail) {
		customerDTO dto = cm.customerSearchId(inputName, inputEmail);
		String result="";
		if(dto!=null) {
				result = dto.getcId();
		}
		return result;
	}

	public customerDTO customerSearchPw(String inputId, String inputName, String inputTel) {
			customerDTO dto = cm.getCustomer(inputId);
			if(dto!=null) {
				if(inputName.equals(dto.getcName()) && inputTel.equals(dto.getcTel())) {
					System.out.println("���̵�, �̸�, ��ȭ��ȣ �� ��ġ > �ӽú�й�ȣ �߱�");
					return dto;
				}
			}
			return null;
	}
	
	public String makeRandomPw() {
		StringBuffer sb = new StringBuffer();
		SecureRandom sr = new SecureRandom();
		char[] charSet = new char[] {
	                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
	                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
	                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
	                'U', 'V', 'W', 'X', 'Y', 'Z','a', 'b', 'c', 'd', 
	                'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 
	                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
	                '!', '@', '#', '$', '%', '^', '&' };
		sr.setSeed(new Date().getTime());
		int idx = 0;
		int len = charSet.length;
		for(int i=0; i<8;i++) { // 8�� �ӽú�й�ȣ �ڸ���
			idx = sr.nextInt(len);
			sb.append(charSet[idx]);
			}
			return sb.toString();
		}
	
	public int customerPwChg(String tempPwd, customerDTO dto) {
		dto.setcPw(encoder.encode(tempPwd));
		int result = cm.customerPwChg(dto);
		return result;
	}
	
	public int logChk(String id, String pw) {
		customerDTO dto = cm.getCustomer(id);
		int result = 0;
		if (dto != null) {
			if (encoder.matches(pw, dto.getcPw()) || pw.equals(dto.getcPw())) {
				result = 1;
			}
		}
		return result;
	}
	public void keepLogin(String cSessionId, String cId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cSessionId", cSessionId);
		map.put("cId", cId);
		cm.keepLogin(map);
	}

	public customerDTO getCustomerInfo(String cId) {
		customerDTO dto = cm.getCustomer(cId);
		return dto;
	}

	public customerDTO getCustomerSessionId(String cSessionId) {
		return cm.getCustomerSessionId(cSessionId);
	}

	public String customerPwdChk(String id, String pw) {
		customerDTO dto = cm.getCustomer(id);
		if (dto != null) {
			if (encoder.matches(pw, dto.getcPw()) || pw.equals(dto.getcPw())) {
				String url = "/am/customerModify?id=" + id;
				return "<script>location.href='" + url + "';</script>";
			}
		}
		return GetMessage.getMessage("��й�ȣ�� Ʋ�Ƚ��ϴ�", "/am/customerPwdChk?id=" + id);
	}

	public String customerPwdChg(customerDTO dto, String pw, String newPw) {
		dto = cm.getCustomer(dto.getcId());
		int result =0;
		if (encoder.matches(pw, dto.getcPw()) || pw.equals(dto.getcPw())) {
				dto.setcPw(encoder.encode(newPw));
				result = cm.customerPwdChg(dto);
				if(result == 1) {
					return GetMessage.getMessage("��й�ȣ�� ����Ǿ����ϴ�", "/am/customerInfo?id=" +dto.getcId());
				}
			}
		return GetMessage.getMessage("��й�ȣ�� Ʋ�Ƚ��ϴ�", "/am/customerPwdChg?id=" + dto.getcId());
	}

	public String customerModify(customerDTO dto) {
		int result = 0;
		result = cm.customerModify(dto);
		if (result == 1) {
			return GetMessage.getMessage("������ �����Ǿ����ϴ�.", "/am/customerInfo?id=" + dto.getcId());
		}
		return GetMessage.getMessage("���������� �����߽��ϴ�.", "/am/customerModify?id=" + dto.getcId());
	}
	public String customerDelete(customerDTO dto, String pw) {
		 dto = cm.getCustomer(dto.getcId());
		 int result =0 ;
			if (encoder.matches(pw, dto.getcPw()) || pw.equals(dto.getcPw())) {
				result =  cm.customerDelete(dto);
				if(result == 1) {
					return GetMessage.getMessage("Ż�� �Ϸ�Ǿ����ϴ�", "/am" );
				}
			}
		return GetMessage.getMessage("��й�ȣ�� Ʋ�Ƚ��ϴ�", "/am/customerPwdChk?id=" + dto.getcId());
	}
	
	
	
	
	
	
	
	
	
	/*
	 * īī�� API
	 * ��ū �޾ƿ���
	 */
	 
//	public String getKakaoAccessToken (String code) {
//	    String accessToken = "";
//	    String refreshToken = "";
//	    String requestURL = "https://kauth.kakao.com/oauth/token";
//
//	    try {
//	        URL url = new URL(requestURL);
//	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//	        conn.setRequestMethod("POST");
//	        // setDoOutput()�� OutputStream���� POST �����͸� �Ѱ� �ְڴٴ� �ɼ��̴�.
//	        // POST ��û�� �����Ϸ��� setDoOutput()�� true�� �����Ѵ�.
//	        conn.setDoOutput(true);
//
//	        // POST ��û���� �ʿ��� �Ķ���͸� OutputStream�� ���� ����
//	        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//	        String sb = "grant_type=authorization_code" +
//	                "&client_id=7a540abf05d1cc8e92ef6b0c2a558b63" + // REST_API_KEY
//	                "&redirect_uri=	http://localhost:8090/oauth/kakao" + // REDIRECT_URI
//	                "&code=" + code;
//	        bufferedWriter.write(sb);
//	        bufferedWriter.flush();
//
//	        int responseCode = conn.getResponseCode();
//	        System.out.println("responseCode : " + responseCode);
//
//	        // ��û�� ���� ���� �����͸� InputStreamReader�� ���� �о� ����
//	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//	        String line = "";
//	        StringBuilder result = new StringBuilder();
//
//	        while ((line = bufferedReader.readLine()) != null) {
//	            result.append(line);
//	        }
//	        System.out.println("response body : " + result);
//
//	        JsonElement element = JsonParser.parseString(result.toString());
//
//	        accessToken = element.getAsJsonObject().get("access_token").getAsString();
//	        refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();
//
//	        System.out.println("accessToken : " + accessToken);
//	        System.out.println("refreshToken : " + refreshToken);
//
//	        bufferedReader.close();
//	        bufferedWriter.close();
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }
//
//	    return accessToken;
//	}
//	
//	
//	
//	public HashMap<String, Object> getUserInfo(String accessToken) {
//	    HashMap<String, Object> userInfo = new HashMap<>();
//	    String postURL = "https://kapi.kakao.com/v2/user/me";
//
//	    try {
//	        URL url = new URL(postURL);
//	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//	        conn.setRequestMethod("POST");
//
//	        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
//
//	        int responseCode = conn.getResponseCode();
//	        System.out.println("responseCode : " + responseCode);
//
//	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//	        String line = "";
//	        StringBuilder result = new StringBuilder();
//
//	        while ((line = br.readLine()) != null) {
//	            result.append(line);
//	        }
//	        System.out.println("response body : " + result);
//
//	        JsonElement element = JsonParser.parseString(result.toString());
//	        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
//	        JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
//
//	        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
//	        String email = kakaoAccount.getAsJsonObject().get("email").getAsString();
//
//	        userInfo.put("nickname", nickname);
//	        userInfo.put("email", email);
//
//	    } catch (IOException exception) {
//	        exception.printStackTrace();
//	    }
//
//	    return userInfo;
//	}
//	
//	 // ��ū����
//
//	public void updateKakaoToken(int userId) throws BaseException {
//        KakaoToken kakaoToken = loginProvider.getKakaoToken(userId);
//        String postURL = "https://kauth.kakao.com/oauth/token";
//        KakaoToken newToken = null;
//
//        try {
//            URL url = new URL(postURL);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("POST");
//            conn.setDoOutput(true);
//
//            // POST ��û�� �ʿ��� �Ķ���͸� OutputStream�� ���� ����
//            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//            String sb = "grant_type=refresh_token" +
//                    "&client_id=REST_API_KEY �Է�" + // REST_API_KEY
//                    "&refresh_token=" + kakaoToken.getRefresh_token() + // REFRESH_TOKEN
//                    "&client_secret=��ũ�� Ű �Է�";
//            bufferedWriter.write(sb);
//            bufferedWriter.flush();
//
//            // ��û�� ���� ���� �����͸� InputStreamReader�� ���� �о� ����
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String line = "";
//            StringBuilder result = new StringBuilder();
//
//            while ((line = bufferedReader.readLine()) != null) {
//                result.append(line);
//            }
//            System.out.println("response body : " + result);
//
//            JsonElement element = JsonParser.parseString(result.toString());
//
//            Set<String> keySet = element.getAsJsonObject().keySet();
//
//						// ���� �߱� ���� accessToken �ҷ�����
//            String accessToken = element.getAsJsonObject().get("access_token").getAsString();
//	          // refreshToken�� ��ȿ �Ⱓ�� 1���� �̸��� ��쿡�� ���ŵǾ� ��ȯ�ǹǷ�,
//						// ��ȯ���� �ʴ� ����� ��Ȳ�� if������ ó�����־���.
//						String refreshToken = "";
//            if(keySet.contains("refresh_token")) {
//                refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();
//            }
//
//            if(refreshToken.equals("")) {
//                newToken = new KakaoToken(accessToken, kakaoToken.getRefresh_token());
//            } else {
//                newToken = new KakaoToken(accessToken, refreshToken);
//            }
//
//            bufferedReader.close();
//            bufferedWriter.close();
//
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//
//        try{
//            int result = 0;
//            if (newToken != null) {
//                result = loginDao.updateKakaoToken(userId, newToken);
//            }
//            if(result == 0){
//                throw new BaseException(UPDATE_FAIL_TOKEN);
//            }
//        } catch(Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//
//    }
	
	

}
