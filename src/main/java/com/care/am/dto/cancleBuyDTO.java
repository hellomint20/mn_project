package com.care.am.dto;

import org.json.simple.JSONObject;

public class cancleBuyDTO {
	private String code;
	private String messege;
	private JSONObject response = new JSONObject();

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessege() {
		return messege;
	}
	public void setMessege(String messege) {
		this.messege = messege;
	}
	public JSONObject getResponse() {
		return response;
	}
	public void setResponse(JSONObject response) {
		this.response = response;
	}
	
	
}
