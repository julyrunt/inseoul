package com.inseoul.action.bucket;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.Action;
import com.inseoul.dao.ConnectDB;
import com.inseoul.svc.bucket.BucketDetailService;
import com.inseoul.svc.bucket.BucketReplyListService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.BucketBean;
import com.inseoul.vo.ReplyBean;
import com.inseoul.vo.PageInfo;
import com.inseoul.vo.ProfileBean;

public class BucketDetailAction implements Action {
  /* ----------------------------------------------------------------------------------------------------
   * 버킷 게시물과 댓글 목록의 정보를 취득하고 이를 전달합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
    /* 게시물 정보 취득 */
    HttpSession session = request.getSession();
    String uid = (String) session.getAttribute("uid");
    String bid = request.getParameter("bid");
    String page = request.getParameter("page");
    BucketDetailService bucketDetailService = new BucketDetailService();
    BucketBean bucket = bucketDetailService.getBucket(Integer.parseInt(bid), Integer.parseInt(uid));
    ActionForward forward = new ActionForward();
    request.setAttribute("page", page);
    request.setAttribute("bucket", bucket);

    /* 작성자 정보 취득 */
    ConnectDB conn = new ConnectDB();
    ProfileBean author = conn.profileInfoById(String.valueOf(bucket.getUid()), uid);
    request.setAttribute("author", author);
    
    /* 댓글 정보 취득 */
    ArrayList<ReplyBean> replyList = new ArrayList<ReplyBean>();
    int replyPage = 1;
    int limit = 10;
    int range = 10;
    if(request.getParameter("rp") != null){
      replyPage = Integer.parseInt(request.getParameter("rp"));
    }
    BucketReplyListService bucketReplyListService = new BucketReplyListService();
    int listCount = bucketReplyListService.getListCount(Integer.parseInt(bid));
    replyList = bucketReplyListService.getReplyList(Integer.parseInt(bid), replyPage, limit);
    int maxPage = (int)((double)(listCount + limit - 1) / limit);
    int startPage = replyPage - (replyPage % range) + 1;
    int endPage = Math.min(startPage + range - 1, maxPage);
    
    PageInfo pageInfo = new PageInfo();
    pageInfo.setEndPage(endPage);
    pageInfo.setListCount(listCount);
    pageInfo.setMaxPage(maxPage);
    pageInfo.setPage(replyPage);
    pageInfo.setStartPage(startPage);
    request.setAttribute("pageInfo", pageInfo);
    request.setAttribute("replyList", replyList);
    
    forward.setPath("view.jsp");
    return forward;
  }
}