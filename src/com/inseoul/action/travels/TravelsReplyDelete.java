package com.inseoul.action.travels;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inseoul.controller.CommandInter;
import com.inseoul.model.travels.TravelsReplyModel;
import com.inseoul.vo.ReplyBean;

public class TravelsReplyDelete implements CommandInter{
	
	static TravelsReplyDelete impl = new TravelsReplyDelete();

	public static TravelsReplyDelete instance() {
		return impl;
	}
	
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("TravelsReplyDelete showData 옴~");
		TravelsReplyModel model = TravelsReplyModel.instance();
		
		String rid = (String) request.getParameter("rid");
		String tid = (String) request.getParameter("tid");
		
		model.deleteArticle(Integer.parseInt(rid));
		
		return "travelsReply.tv?tid="+tid;
	}
}
