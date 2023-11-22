package com.care.am.naver;

import com.github.scribejava.core.builder.api.DefaultApi20;

public class NaverLoginAPI extends DefaultApi20 {
	
	protected NaverLoginAPI() {
		
	}
	
	private static class InstanceHolder {
		private static final NaverLoginAPI INSTANCE = new NaverLoginAPI();
	}
	
	static NaverLoginAPI instance() {
		return InstanceHolder.INSTANCE;
	}
	
	public String getAccessTokenEndpoint () {
		return "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code";
	}
	
	protected String getAuthorizationBaseUrl() {
		return "https://nid.naver.com/oauth2.0/authorize";
	}

	
}
