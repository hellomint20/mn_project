package com.care.am.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.care.am.dto.customerDTO;
import com.care.am.service.customer.customerService;
import com.care.am.service.medi.mediService;

public class AutoLoginInterceptor extends HandlerInterceptorAdapter
																implements LoginSession{
	
	@Autowired customerService cs;
	@Autowired mediService ms;
	
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler)
			throws Exception {
		Cookie loginCookie= WebUtils.getCookie(request, "loginCookie");
		if(loginCookie != null) {
			customerDTO dto = cs.getCustomerSessionId(loginCookie.getValue());
			if(dto != null) {
				request.getSession().setAttribute(LOGIN,dto.getcId());
			}
		}
		return true;
	}
}
