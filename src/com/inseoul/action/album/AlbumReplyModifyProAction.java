package com.inseoul.action.album;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.Action;
import com.inseoul.svc.album.AlbumReplyModifyProService;
import com.inseoul.util.SafeHtml;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.ReplyBean;

public class AlbumReplyModifyProAction implements Action {

  public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{    
    ActionForward forward = null;
    ReplyBean replyBean = null;
    String rid = request.getParameter("rid");
    HttpSession session = request.getSession();
    String uid = (String) session.getAttribute("uid");
    String contents = request.getParameter("contents");
    AlbumReplyModifyProService albumReplyModifyProService = new AlbumReplyModifyProService();
    if(uid == null || rid == null || contents == null || contents.trim().length() == 0) {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script>");
      out.println("alert('fail')");
      out.println("history.back();");
      out.println("</script>");
    } else {
      boolean isReplyWriter = albumReplyModifyProService.isReplyWriter(Integer.parseInt(rid), Integer.parseInt(uid));
      if(!isReplyWriter){
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>");
        out.println("alert('fail');");
        out.println("history.back();");
        out.println("</script>");
        out.close();
      } else {
        replyBean = new ReplyBean();
        replyBean.setRid(Integer.parseInt(rid));
        replyBean.setUid(Integer.parseInt(uid));
        replyBean.setContents(SafeHtml.htmlEscape(contents));
        boolean isModifySuccess = albumReplyModifyProService.modifyReply(replyBean);
        if(!isModifySuccess){
          response.setContentType("text/html;charset=UTF-8");
          PrintWriter out = response.getWriter();
          out.println("<script>");
          out.println("alert('��������');");
          out.println("history.back();");
          out.println("</script>");
          out.close();
        } else {
          forward = new ActionForward();
          forward.setRedirect(true);
          String aid = request.getParameter("aid");
          String url = "reply.do?aid=" + aid;
          forward.setPath(url);
        }
      }
    }
    return forward;
  }

}