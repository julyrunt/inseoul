package com.inseoul.action.lodgments;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.controller.CommandInter;

public class Review_Write implements CommandInter{
	
	static Review_Write impl = new Review_Write();

	public static Review_Write instance() {
		return impl;
	}
	
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Review_Write showData 옴");
		
		String uid = (String) request.getParameter("uid");
		String title = (String) request.getParameter("title");
		String contents = (String) request.getParameter("contents");
		
		
		
		return "review-ajax.jsp";
	}
}
