package com.inseoul.action.album;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.Action;
import com.inseoul.svc.album.AlbumReplyDeleteProService;
import com.inseoul.vo.ActionForward;

public class AlbumReplyDeleteProAction implements Action {

  public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{    
    ActionForward forward = null;
    String rid = request.getParameter("rid");
    HttpSession session = request.getSession();
    String uid = (String) session.getAttribute("uid");
    AlbumReplyDeleteProService albumReplyDeleteProService = new AlbumReplyDeleteProService();
    boolean isReplyWriter = albumReplyDeleteProService.isReplyWriter(Integer.parseInt(rid), Integer.parseInt(uid));
    if(!isReplyWriter){
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script>");
      out.println("alert('fail');");
      out.println("history.back();");
      out.println("</script>");
      out.close();
    } else {
      boolean isDeleteSuccess = albumReplyDeleteProService.removeReply(Integer.parseInt(rid));
      if(!isDeleteSuccess){
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
    return forward;
  }

}