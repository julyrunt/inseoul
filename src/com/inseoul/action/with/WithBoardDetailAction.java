package com.inseoul.action.with;

import java.awt.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inseoul.action.Action;

import com.inseoul.svc.with.WithBoardDetailService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.PageInfo;
import com.inseoul.vo.WithBoardBean;

public class WithBoardDetailAction implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
	   	System.out.println("WithBoardDetailAction 도착 ");
	   	HttpSession session = request.getSession();
	   	String uid=(String) session.getAttribute("uid");
		int wid=Integer.parseInt(request.getParameter("wid"));
		String page = request.getParameter("page");
		
		
		ArrayList<WithBoardBean> replyList=new ArrayList<WithBoardBean>();
		int replypage = 1;
		int replylimit = 10;
		
		if(request.getParameter("replypage")!=null){
			replypage=Integer.parseInt(request.getParameter("replypage"));
		}
		
		WithBoardDetailService boardDetailService = new WithBoardDetailService();
		if(uid != null) {
   			int stateCount = boardDetailService.getGroup(Integer.parseInt(uid), wid);
   			request.setAttribute("stateCount", stateCount);
   			WithBoardBean article = boardDetailService.getArticle(Integer.parseInt(uid), wid);
   			request.setAttribute("article", article);
   		}
		else {
			WithBoardBean article = boardDetailService.getArticle(wid);
			request.setAttribute("article", article);
		}
		
		int replylistCount = boardDetailService.getReplyListCount(wid);
		replyList = boardDetailService.getReplyList(wid, replypage, replylimit);
		
		
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
   		
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
		request.setAttribute("replypageInfo", replypageInfo);
	   	request.setAttribute("replyList", replyList);
	   	
	   	
   		forward.setPath("with_detail.jsp");
   		System.out.println("WithBoardDetailAction 떠남");
   		
   		return forward;

	 }
}
