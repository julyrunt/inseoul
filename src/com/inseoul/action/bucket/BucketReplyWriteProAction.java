package com.inseoul.action.bucket;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.Action;
import com.inseoul.svc.bucket.BucketReplyWriteProService;
import com.inseoul.util.SafeHtml;
import com.inseoul.vo.ActionForward;
import com.inseoul.vo.ReplyBean;

public class BucketReplyWriteProAction implements Action {
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
    ActionForward forward=null;
    ReplyBean replyBean = null;
    String bid = request.getParameter("bid");
    String contents = request.getParameter("contents");
    HttpSession session = request.getSession();
    String uid = (String) session.getAttribute("uid");
    if(uid == null || bid == null || contents == null || contents.trim().length() == 0) {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script>");
      out.println("alert('fail')");
      out.println("history.back();");
      out.println("</script>");
    } else {
      replyBean = new ReplyBean();
      replyBean.setBid(Integer.valueOf(bid));
      replyBean.setUid(Integer.valueOf(uid));
      replyBean.setContents(SafeHtml.htmlEscape(contents));
      
      BucketReplyWriteProService bucketReplydWriteProService = new BucketReplyWriteProService();
      boolean isWriteSuccess = bucketReplydWriteProService.registArticle(replyBean);
      System.out.println(isWriteSuccess);
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
        String url = "reply.do?bid=" + bid;
        forward.setPath(url);
      }
    }
    return forward;
  }
}