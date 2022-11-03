package com.inseoul.action.album;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.inseoul.action.Action;
import com.inseoul.svc.album.AlbumReplyListService;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.ReplyBean;
import com.inseoul.vo.PageInfo;

public class AlbumReplyListAction implements Action {
  /* ----------------------------------------------------------------------------------------------------
   * 유동적으로 앨범 게시물의 댓글 목록을 작성한다.
   * - limit : 한 페이지에 출력할 댓글의 최대 수량 
   * - rage : 한 페이지에 출력할 페이지 목록의 최대 수량
   * ---------------------------------------------------------------------------------------------------- */
  public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
    ArrayList<ReplyBean> replyList = new ArrayList<ReplyBean>();
    int replyPage = 1;
    int limit = 10;
    int range = 10;
    String aid = request.getParameter("aid");
    if(request.getParameter("rp") != null){
      replyPage = Integer.parseInt(request.getParameter("rp"));
    }
    AlbumReplyListService albumReplyListService = new AlbumReplyListService();
    int listCount = albumReplyListService.getListCount(Integer.parseInt(aid));
    replyList = albumReplyListService.getReplyList(Integer.parseInt(aid), replyPage, limit);
    /* 댓글 삭제로 인해 페이지 변동이 발생한 경우 한단계 이전 페이지의 내용을 취득한다. */
    if (replyList.size() == 0 && replyPage > 1) {
      replyPage--;
      replyList = albumReplyListService.getReplyList(Integer.parseInt(aid), replyPage, limit);
    }
    int maxPage = (int)((double)(listCount + limit - 1) / limit);
    replyPage = Math.min(replyPage, maxPage);
    int startPage = replyPage - ((replyPage - 1) % range);
    int endPage = Math.min(startPage + range - 1, maxPage);
    PageInfo pageInfo = new PageInfo();
    pageInfo.setEndPage(endPage);
    pageInfo.setListCount(listCount);
    pageInfo.setMaxPage(maxPage);
    pageInfo.setPage(replyPage);
    pageInfo.setStartPage(startPage);
    request.setAttribute("pageInfo", pageInfo);
    request.setAttribute("replyList", replyList);
    ActionForward forward = new ActionForward();
    forward.setPath("../reply.jsp");
    return forward;
  }
}