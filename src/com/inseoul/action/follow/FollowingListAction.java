package com.inseoul.action.follow;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.Action;
import com.inseoul.svc.follow.FollowingListService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.FollowBean;
import com.inseoul.vo.PageInfo;

public class FollowingListAction implements Action {
  /* ----------------------------------------------------------------------------------------------------
   * 유동적으로 앨범 게시판 글 목록을 작성한다.
   * - limit : 한 페이지에 출력할 게시물의 최대 수량 
   * - rage : 한 페이지에 출력할 페이지 목록의 최대 수량
   * ---------------------------------------------------------------------------------------------------- */
  public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
    ArrayList<FollowBean> followingList = new ArrayList<FollowBean>();
    int page = 1;
    int limit = 10;
    int range = 10;
    HttpSession session = request.getSession();
    int pid = Integer.parseInt(request.getParameter("pid"));
    int uid = Integer.parseInt((String) session.getAttribute("uid"));
    if(request.getParameter("page") != null){
      page = Integer.parseInt(request.getParameter("page"));
    }
    FollowingListService followingListService = new FollowingListService();
    int listCount = followingListService.getListCount(pid);
    followingList = followingListService.getFollowingList(pid, uid, page, limit);
    int maxPage = (int)((double)(listCount + limit - 1) / limit);
    int startPage = page - ((page - 1) % range);
    int endPage = Math.min(startPage + range - 1, maxPage);

    PageInfo pageInfo = new PageInfo();
    pageInfo.setEndPage(endPage);
    pageInfo.setListCount(listCount);
    pageInfo.setMaxPage(maxPage);
    pageInfo.setPage(page);
    pageInfo.setStartPage(startPage);   
    request.setAttribute("pageInfo", pageInfo);
    request.setAttribute("followingList", followingList);
    ActionForward forward= new ActionForward();
    forward.setPath("./");    
    return forward;
  }
}