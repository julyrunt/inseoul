package com.inseoul.action.bucket;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.Action;
import com.inseoul.svc.bucket.BucketReplyDeleteProService;
import com.inseoul.vo.ActionForward;

public class BucketReplyDeleteProAction implements Action {

  public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{    
    ActionForward forward = null;
    int rid = Integer.parseInt(request.getParameter("rid"));
    HttpSession session = request.getSession();
    String uid = (String) session.getAttribute("uid");
    BucketReplyDeleteProService bucketReplyDeleteProService = new BucketReplyDeleteProService();
    boolean isReplyWriter = bucketReplyDeleteProService.isReplyWriter(rid, Integer.valueOf(uid));
    if(!isReplyWriter){
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script>");
      out.println("alert('fail');");
      out.println("history.back();");
      out.println("</script>");
      out.close();
    } else {
      boolean isDeleteSuccess = bucketReplyDeleteProService.removeReply(rid);
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
        String bid = request.getParameter("bid");
        String url = "reply.do?bid=" + bid;
        forward.setPath(url);
      }
    }
    return forward;
  }

}