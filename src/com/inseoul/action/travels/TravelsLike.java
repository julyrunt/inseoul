package com.inseoul.action.travels;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inseoul.controller.CommandInter;
import com.inseoul.model.travels.TravelsLikeModel;
import com.inseoul.vo.Tourist_SpotBean;

public class TravelsLike implements CommandInter{
	static TravelsLike impl = new TravelsLike();

	public static TravelsLike instance() {
		return impl;
	}
	
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("TravelsLike showData 옴~");
		TravelsLikeModel model = TravelsLikeModel.instance();
		
		HttpSession session = request.getSession();
		String tid = (String) request.getParameter("tid");
		String uid = (String) session.getAttribute("uid");
		
		int likeCount = model.getLike(Integer.parseInt(tid), Integer.parseInt(uid));
		
		request.setAttribute("likeCount", likeCount);
		
		return "travels-like.jsp";
	}
}
