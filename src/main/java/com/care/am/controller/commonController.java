package com.care.am.controller;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.care.am.common.LoginSession;
import com.care.am.service.customer.customerService;
import com.care.am.service.medi.mediService;


@Controller
public class commonController {
	
	@Autowired customerService cs;
	@Autowired mediService ms;
	
	@GetMapping("/") //���� Ȩ������
	public String main() {
		return "am/common/main";
	}
	//css Ȯ�� ������ ���� & ���� ����	
//	@GetMapping("/main2") //���� Ȩ������2
//	public String main2() {
//		return "am/common/main2";
//	}
	
	@GetMapping("mediSearch") //���� ã�� ������
	public String mediSearch() {
		return "am/common/mediSearch";
	}
	
	@RequestMapping("logout") //�α׾ƿ�
	public String logout(HttpSession session, 
						@CookieValue(value="loginCookie",required=false)Cookie cookie,
						HttpServletResponse res){
		if(cookie != null) {
			cookie.setMaxAge(0);
			cookie.setPath("/");
			res.addCookie(cookie);
			cs.keepLogin("nan", (String)session.getAttribute(LoginSession.cLOGIN));
			ms.keepLogin("nan", (String)session.getAttribute(LoginSession.mLOGIN));
		}	
		session.removeAttribute(LoginSession.cLOGIN);
		session.removeAttribute(LoginSession.mLOGIN);
		session.invalidate();
	
		return "redirect:/";
	}
	
	@RequestMapping("naverLogin2") 
	public String isComplete(HttpSession session) { 	
	return "/am/common/naverLogin2";  
	}
	
	@RequestMapping("naverCallBack") 
	public String navLogin(HttpServletRequest request) throws Exception {	 	
		return "/am/common/naverCallBack"; }	 
	
	
	@RequestMapping(value = "/personalInfo") 
	public void personalInfo(HttpServletRequest request) throws Exception {         
		String token = "YOUR_ACCESS_TOKEN";// ���̹� �α��� ���� ��ū; ���⿡ ������ ��ū���� �־��ݴϴ�.         
		String header = "Bearer " + token; // Bearer ������ ���� �߰�         
		try {             
			String apiURL = "https://openapi.naver.com/v1/nid/me";             
			URL url = new URL(apiURL);             
			HttpURLConnection con = (HttpURLConnection)url.openConnection();             
			con.setRequestMethod("GET");             
			con.setRequestProperty("Authorization", header);             
			int responseCode = con.getResponseCode();             
			BufferedReader br;             
			if(responseCode==200) { // ���� ȣ��                 
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));             
				} else {  // ���� �߻�                
					br = new BufferedReader(new InputStreamReader(con.getErrorStream()));             
					}             
			String inputLine;             
			StringBuffer response = new StringBuffer();             
			while ((inputLine = br.readLine()) != null) {                 
				response.append(inputLine);             
				}             br.close();             
				System.out.println(response.toString());         
				} catch (Exception e) {             
					System.out.println(e);         
					} 
		} 
	
}
	
	
	/*rest api*/
	
//	
//	 public MultiValueMap<String, String> accessTokenParams(String grantType,String clientSecret, String clientId,String code,String redirect_uri) {
//        MultiValueMap<String, String> accessTokenParams = new LinkedMultiValueMap<>();
//        accessTokenParams.add("grant_type", grantType);
//        accessTokenParams.add("client_id", clientId);
//         accessTokenParams.add("client_secret", clientSecret);
//        accessTokenParams.add("code", code); // �������� ���� �ڵ�
//        accessTokenParams.add("redirect_uri", redirect_uri); 
//        return accessTokenParams;
//    }
//
//	 public void naverToken(String code, HttpServletResponse response) throws IOException {
//        RestTemplate rt = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//        MultiValueMap<String, String> accessTokenParams = accessTokenParams("authorization_code",NAVER_CLIENT_SECRET,NAVER_CLIENT_ID ,code,NAVER_REDIRECT_URI);
//        HttpEntity<MultiValueMap<String, String>> accessTokenRequest = new HttpEntity<>(accessTokenParams, headers);
//        ResponseEntity<String> accessTokenResponse = rt.exchange(
//                NAVER_TOKEN_URI,
//                HttpMethod.POST,
//                accessTokenRequest,
//                String.class);
//        try {
//            JSONParser jsonParser = new JSONParser();
//            String header = "Bearer " + code;
//            Map<String, String> requestHeaders = new HashMap<>();
//            requestHeaders.put("Authorization", header);
//            String responseBody = get(NAVER_USER_INFO_URI, requestHeaders);
//            JSONObject parse = (JSONObject) JSONParser.parse(responseBody);
//            	
//            JSONObject responseParse = (JSONObject) parse.get("response");
//            String encodeUserName = (String) responseParse.get("name");
//            String loginId = (String) responseParse.get("id");
//            String email = (String) responseParse.get("email");
//            String phoneNumber = (String) responseParse.get("mobile_e164");
//            String userName = new String(encodeUserName.getBytes(StandardCharsets.UTF_8));
//            User user = new UserRequest("social_" + loginId, userName, encode.encode("���̹�"), email, phoneNumber).naverOAuthToEntity();
//            if (userRepository.existsByLoginId(user.getLoginId()) == false) {
//                userRepository.save(user);
//            }
//            String access_token = tokenProvider.create(new PrincipalDetails(user));
//            response.addHeader("Authorization","Bearer " + access_token);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//	
//	
//	
//	
//	
	
