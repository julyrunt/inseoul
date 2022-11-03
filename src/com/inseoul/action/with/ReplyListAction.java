package com.inseoul.action.with;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inseoul.action.Action;

import com.inseoul.svc.with.BoardListService;
import com.inseoul.svc.with.ReplyListService;
import com.inseoul.svc.with.WithBoardDetailService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.WithBoardBean;
import com.inseoul.vo.PageInfo;

public class ReplyListAction implements Action {
	
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		System.out.println("ReplyListAction 도착!");
		ArrayList<WithBoardBean> replyList=new ArrayList<WithBoardBean>();
		int ref=Integer.parseInt(request.getParameter("ref"));
		
	  	int replypage=1;
		int replylimit=10;
		
		if(request.getParameter("replypage")!=null){
			replypage=Integer.parseInt(request.getParameter("replypage"));
		}
		System.out.println("1");
		ReplyListService replyListService = new ReplyListService();
		WithBoardBean article = replyListService.getArticle(ref);
		System.out.println("2");
		int replylistCount=replyListService.getReplyListCount(ref);
		System.out.println("#");
		replyList = replyListService.getReplyList(ref, replypage, replylimit);
		System.out.println("4");
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
   		
		request.setAttribute("replypageInfo", replypageInfo);
		request.setAttribute("replyList", replyList);
		request.setAttribute("article", article);
		
		ActionForward forward= new ActionForward();
   		forward.setPath("with-reply.jsp");
   		return forward;
   		
	 }

}
