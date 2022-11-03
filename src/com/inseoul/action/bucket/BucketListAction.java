package com.inseoul.action.bucket;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.Action;
import com.inseoul.dao.ConnectDB;
import com.inseoul.svc.bucket.BucketListService;
import com.inseoul.util.SafeHtml;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.BucketBean;
import com.inseoul.vo.PageInfo;
import com.inseoul.vo.ProfileBean;

public class BucketListAction implements Action {
  /* ----------------------------------------------------------------------------------------------------
   * 유동적으로 앨범 게시판 글 목록을 작성한다.
   * - limit : 한 페이지에 출력할 게시물의 최대 수량 
   * - rage : 한 페이지에 출력할 페이지 목록의 최대 수량
   * ---------------------------------------------------------------------------------------------------- */
  public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
    ArrayList<BucketBean> bucketList = new ArrayList<BucketBean>();
    int page = 1;
    int limit = 10;
    int range = 10;
    HttpSession session = request.getSession();
    String uid = (String) session.getAttribute("uid");
    String pid = request.getParameter("pid");
    if (pid == null) {
      pid = uid;
    }
    /* 프로필 정보 취득 */
    ConnectDB conn = new ConnectDB();
    ProfileBean profile = conn.profileInfoById(pid, uid);
    request.setAttribute("profile", profile);
    
    String category = request.getParameter("category");
    String search = request.getParameter("search");
    String keywords = SafeHtml.htmlEscape(request.getParameter("keywords"));
    if(request.getParameter("page") != null){
      page = Integer.parseInt(request.getParameter("page"));
    }
    BucketListService bucketListService = new BucketListService();
    int listCount = bucketListService.getListCount(Integer.parseInt(pid), category, search, keywords);
    bucketList = bucketListService.getBucketList(page, limit, Integer.parseInt(pid), Integer.parseInt(uid), category, search, keywords);
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
    request.setAttribute("bucketList", bucketList);
    ActionForward forward = new ActionForward();
    forward.setPath("./");    
    return forward;
  }
}