package com.inseoul.action.bucket;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.Action;
import com.inseoul.svc.bucket.BucketReplyModifyProService;
import com.inseoul.util.SafeHtml;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.ReplyBean;

public class BucketReplyModifyProAction implements Action {

  public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{    
    ActionForward forward = null;
    String rid = request.getParameter("rid");
    HttpSession session = request.getSession();
    String uid = (String) session.getAttribute("uid");
    String contents = request.getParameter("contents");
    BucketReplyModifyProService bucketReplyModifyProService = new BucketReplyModifyProService();
    if(uid == null || rid == null || contents == null || contents.trim().length() == 0) {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script>");
      out.println("alert('fail');");
      out.println("history.back();");
      out.println("</script>");
      out.close();
    } else {
      boolean isReplyWriter = bucketReplyModifyProService.isReplyWriter(Integer.parseInt(rid), Integer.parseInt(uid));
      if(!isReplyWriter){
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>");
        out.println("alert('fail');");
        out.println("history.back();");
        out.println("</script>");
        out.close();
      } else {
        ReplyBean replyBean = null;
        replyBean = new ReplyBean();
        replyBean.setRid(Integer.parseInt(rid));
        replyBean.setUid(Integer.parseInt(uid));
        replyBean.setContents(SafeHtml.htmlEscape(contents));
        boolean isModifySuccess = bucketReplyModifyProService.modifyReply(replyBean);
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
          String bid = request.getParameter("bid");
          String url = "reply.do?bid=" + bid;
          forward.setPath(url);
        }
      }
    }
    
    return forward;
  }

}