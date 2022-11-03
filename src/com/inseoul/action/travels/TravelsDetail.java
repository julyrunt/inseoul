package com.inseoul.action.travels;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inseoul.controller.CommandInter;
import com.inseoul.model.travels.LocationDetailModel;
import com.inseoul.model.travels.TravelsDetailModel;
import com.inseoul.vo.PageInfo;
import com.inseoul.vo.ReplyBean;
import com.inseoul.vo.Tourist_SpotBean;
import com.inseoul.vo.TravelsBean;
import com.inseoul.vo.WithBoardBean;

public class TravelsDetail implements CommandInter{
	
	static TravelsDetail impl = new TravelsDetail();

	public static TravelsDetail instance() {
		return impl;
	}
	
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("TravelsDetail showData 옴~");
		TravelsDetailModel model = TravelsDetailModel.instance();
		HttpSession session = request.getSession();
	   	String uid= (String) session.getAttribute("uid");
	   	String tid = (String) request.getParameter("tid");
		String page = request.getParameter("page");
		TravelsBean article = new TravelsBean();
		ArrayList<ReplyBean> replylist = new ArrayList<ReplyBean>();
		
		int replypage = 1;
		int replylimit = 10;
		if(request.getParameter("replypage")!=null){
			replypage=Integer.parseInt(request.getParameter("replypage"));
		}
		if(uid != null) {
			int likeCount = model.getLike(Integer.parseInt(uid), Integer.parseInt(tid));
			request.setAttribute("likeCount", likeCount);
			article = model.getArticle(Integer.parseInt(uid), Integer.parseInt(tid));
			request.setAttribute("article", article);
		}else {
		article = model.getArticle(Integer.parseInt(tid));
		request.setAttribute("article", article);
		}
		
		int replylistCount = model.getReplyListCount(Integer.parseInt(tid));
		replylist = (ArrayList<ReplyBean>) model.getReplyList(Integer.parseInt(tid), replypage);
		int replymaxPage=(int)((double)replylistCount/replylimit+0.95); 
   		int replystartPage = (((int) ((double)replypage / 10 + 0.9)) - 1) * 10 + 1;
   	    int replyendPage = replystartPage+10-1;
   	    
   		if (replyendPage> replymaxPage) replyendPage= replymaxPage;
   		
   		PageInfo replypageInfo = new PageInfo();
   		replypageInfo.setEndPage(replyendPage);
   		replypageInfo.setListCount(replylistCount);
   		replypageInfo.setMaxPage(replymaxPage);
   		replypageInfo.setPage(replypage);
   		replypageInfo.setStartPage(replystartPage);
		
   		request.setAttribute("page", page);
		request.setAttribute("replypageInfo", replypageInfo);
	   	request.setAttribute("replylist", replylist);
   		
		return "travels-detail.jsp";
	}
}
