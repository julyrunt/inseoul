package com.inseoul.action.album;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.Action;
import com.inseoul.dao.ConnectDB;
import com.inseoul.svc.album.AlbumDetailService;
import com.inseoul.svc.album.AlbumReplyListService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.AlbumBean;
import com.inseoul.vo.ReplyBean;
import com.inseoul.vo.PageInfo;
import com.inseoul.vo.ProfileBean;

public class AlbumDetailAction implements Action {
  /* ----------------------------------------------------------------------------------------------------
   * 앨범 게시물과 댓글 목록의 정보를 취득하고 이를 전달합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
    /* 앨범 정보 취득 */
    HttpSession session = request.getSession();
    String uid = (String) session.getAttribute("uid");
    String aid = request.getParameter("aid");
    String page = request.getParameter("page");
    AlbumDetailService albumDetailService = new AlbumDetailService();
    AlbumBean album = albumDetailService.getAlbum(Integer.parseInt(aid), Integer.parseInt(uid));
    ActionForward forward = new ActionForward();
    request.setAttribute("page", page);
    request.setAttribute("album", album);
    
    /* 작성자 정보 취득 */
    ConnectDB conn = new ConnectDB();
    ProfileBean author = conn.profileInfoById(String.valueOf(album.getUid()), uid);
    request.setAttribute("author", author);
    
    /* 댓글 정보 취득 */
    ArrayList<ReplyBean> replyList = new ArrayList<ReplyBean>();
    int replyPage = 1;
    int limit = 10;
    int range = 10;
    if(request.getParameter("rp") != null){
      replyPage = Integer.parseInt(request.getParameter("rp"));
    }
    AlbumReplyListService albumReplyListService = new AlbumReplyListService();
    int listCount = albumReplyListService.getListCount(Integer.parseInt(aid));
    replyList = albumReplyListService.getReplyList(Integer.parseInt(aid), replyPage, limit);
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