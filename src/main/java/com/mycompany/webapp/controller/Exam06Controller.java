package com.mycompany.webapp.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.User;

@Controller
@RequestMapping("/exam06")
public class Exam06Controller {
	private static final Logger logger =
			LoggerFactory.getLogger(Exam06Controller.class);
	
	@GetMapping("/content")
	public String content() {
		return "exam06/content";
	}
	
	@GetMapping("/createCookie")
	public String createCookie(HttpServletResponse response) {
		String uid = "spring";
		Cookie  cookie = new Cookie("uid",uid); //쿠키 만들기
		//cookie.setDomain("192.168.2.7"); //브라우저가 지정된 도메인을 사용해야지만 쿠키를 저장할 수 있게함
		//cookie.setPath("/");//path에 지정된 경로로 가야지만 쿠키 사용 가능
		//cookie.setHttpOnly(false);//true값이면 자바스크립트에서 값을 읽을 수 없다
		//cookie.setMaxAge(10);
		//쿠기를 응답에 실어서 클라이언트로 보내주기. 클라이언트로 보낼때는 헤더에 실어서 보내야함. HttpServletResponse사용
		response.addCookie(cookie); //응답에 쿠키를 넣어서 보낸다. 응답 http헤더 부분에 넘어감
		//쿠키는 여러개 저장 가능
		Cookie cookie2 = new Cookie("utel", "0100123-1234");
		response.addCookie(cookie2);
		return "redirect:/exam06/content";
	}
	
	/*@GetMapping("/readCookie")
	public String readCookie(HttpServletRequest request) {
		Cookie[] cookieArr = request.getCookies(); 
		for(Cookie cookie : cookieArr) {
			logger.info(cookie.getName()+ ":" + cookie.getValue());
			if(cookie.getName().equals("uid")) {
				logger.info(cookie.getValue()+"활용하기");
			}
		}
		return "redirect:/exam06/content";
	}*/
	
	@GetMapping("/readCookie")
	public String readCookie(@CookieValue String uid) {	//쿠키에 uid가 넘어오면 uid를 저장해라
		logger.info(uid+"활용하기");
		return "redirect:/exam06/content";
	}
	
	@GetMapping("/deleteCookie")
	public String deleteCookie(HttpServletResponse response) {
		Cookie cookie = new Cookie("uid","");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return "redirect:/exam06/content";
	}
	
	@GetMapping("/sessionSaveObject")
	public String sessionSaveObject(HttpSession session) {
		User user = new User();
		user.setUid("spring");
		session.setAttribute("user", user);
		return "redirect:/exam06/content";
	}
	
	@GetMapping("/sessionReadObject")
	public String sessionReadObject(HttpSession session) {
		User user = (User)session.getAttribute("user");
		if(user!=null) {
		logger.info("저장된 uid:" + user.getUid());
		}
		return "redirect:/exam06/content";
	}
	
	@GetMapping("/sessionRemoveObject")
	public String sessionRemoveObject(HttpSession session) {
		//개별 객체를 삭제할 때
		session.removeAttribute("user");
		
		//세션에 저장된 모든 객체를 삭제할 경우
		session.invalidate();//세션 자체를 무효화
		return "redirect:/exam06/content";
	}

}
