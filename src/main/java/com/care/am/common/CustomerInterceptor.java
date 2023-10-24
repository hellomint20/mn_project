package com.care.am.common;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class CustomerInterceptor extends HandlerInterceptorAdapter implements LoginSession{
   
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
         throws Exception {
      System.out.println("커스터머 인터셉터 발동!");
      HttpSession session = request.getSession();
      if( session.getAttribute(LOGIN) == null) {
         
         response.setContentType("text/html; charset=UTF-8");
         PrintWriter out = response.getWriter();
         out.print("<script>alert('로그인 먼저 해주세요');"
        		 +"location.href='/am/customerLogin';</script>");
      
         return false;
      }
      return true;
   }
   
   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("컨트롤러 후 실행");
   
   }
}