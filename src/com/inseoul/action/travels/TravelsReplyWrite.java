package com.inseoul.action.travels;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inseoul.controller.CommandInter;
import com.inseoul.model.travels.TravelsReplyModel;
import com.inseoul.model.travels.TravelsModel;
import com.inseoul.vo.ReplyBean;
import com.inseoul.vo.TravelsBean;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class TravelsReplyWrite implements CommandInter{
	
	static TravelsReplyWrite impl = new TravelsReplyWrite();

	public static TravelsReplyWrite instance() {
		return impl;
	}
	
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("TravelsReplyWrite showData 옴~");
		TravelsReplyModel model = TravelsReplyModel.instance();
		ReplyBean reply = new ReplyBean();
		
		HttpSession session = request.getSession();
		int uid = Integer.parseInt((String) session.getAttribute("uid"));
		String tid = (String) request.getParameter("tid");
		String contents = (String) request.getParameter("contents");
		
		reply.setContents(contents);
		reply.setTid(Integer.parseInt(tid));
		reply.setUid(uid);
		model.insertArticle(reply);
		
		return "travelsReply.tv?tid="+tid;
	}
}
