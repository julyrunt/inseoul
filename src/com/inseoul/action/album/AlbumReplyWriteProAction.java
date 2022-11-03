package com.inseoul.action.album;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.Action;
import com.inseoul.svc.album.AlbumReplyWriteProService;
import com.inseoul.util.SafeHtml;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.ReplyBean;

public class AlbumReplyWriteProAction implements Action {
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
    ActionForward forward = null;
    ReplyBean replyBean = null;
    String aid = request.getParameter("aid");
    String contents = request.getParameter("contents");
    HttpSession session = request.getSession();
    String uid = (String) session.getAttribute("uid");
    if(uid == null || aid == null || contents == null || contents.trim().length() == 0) {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script>");
      out.println("alert('fail')");
      out.println("history.back();");
      out.println("</script>");
    } else {
      replyBean = new ReplyBean();
      replyBean.setAid(Integer.valueOf(aid));
      replyBean.setUid(Integer.valueOf(uid));
      replyBean.setContents(SafeHtml.htmlEscape(contents));
      AlbumReplyWriteProService albumReplydWriteProService = new AlbumReplyWriteProService();
      boolean isWriteSuccess = albumReplydWriteProService.registArticle(replyBean);
      if(!isWriteSuccess){
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>");
        out.println("alert('fail')");
        out.println("history.back();");
        out.println("</script>");
      } else{
        forward = new ActionForward();
        forward.setRedirect(true);
        String url = "reply.do?aid=" + aid;
        forward.setPath(url);
      }
    }

    return forward;
  }
}